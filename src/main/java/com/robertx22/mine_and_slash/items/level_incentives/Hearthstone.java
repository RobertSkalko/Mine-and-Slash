package com.robertx22.mine_and_slash.items.level_incentives;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Hearthstone extends Item {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public Hearthstone(int rarity) {

        super(new Properties().group(CreativeTabs.MyModTab)
                .maxStackSize(1)
                .defaultMaxDamage(0));

        this.rarity = rarity;
        this.setup(rarity);
        RegisterItemUtils.RegisterItemName(this, "hearthstone/" + rarity);
    }

    int rarity;

    private void setup(int rarity) {
        levelReq = levelReqs.get(rarity);
        activationTimeSeconds = activationTimes.get(rarity);
        blocksTeleported = distanceTeleportedBlocks.get(rarity);
        totalUses = totalUsesList.get(rarity);
    }

    public static final List<Integer> levelReqs = Arrays.asList(1, 10, 25, 50, 75, 100);
    public static final List<Integer> activationTimes = Arrays.asList(10, 8, 7, 6, 5, 3);
    public static final List<Integer> distanceTeleportedBlocks = Arrays.asList(500, 1000, 2500, 5000, 15000, 100000);
    public static final List<Integer> totalUsesList = Arrays.asList(3, 10, 25, 50, 250, 1000);

    public Integer levelReq;
    public Integer activationTimeSeconds;
    public Integer blocksTeleported;
    public Integer totalUses;

    public static final int tickRate = 20;

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entity, int itemSlot,
                              boolean isSelected) {

        try {
            if (entity.ticksExisted % tickRate == 0 && entity instanceof PlayerEntity) {

                if (worldIn.isRemote) {
                    CompoundNBT nbt = stack.getTag();

                    if (nbt != null && nbt.getBoolean("porting")) {

                        if (nbt.getInt("ticks") < tickRate + 1) {
                            entity.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 0.5F, 1);
                        }

                        ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, (PlayerEntity) entity, 15);

                    }

                } else {

                    CompoundNBT nbt = stack.getTag();

                    if (nbt == null) {
                        nbt = new CompoundNBT();
                        stack.setTag(nbt);
                    }

                    if (nbt.getBoolean("porting")) {

                        BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));

                        if (pos.distanceSq(entity.getPosition()) > 3) {

                            nbt.putBoolean("porting", false);
                            nbt.putInt("ticks", 0);

                            entity.sendMessage(Chats.Teleport_canceled_due_to_movement.locName());

                        } else {

                            if (nbt.contains("ticks")) {

                                ParticleUtils.spawnParticles(ParticleTypes.HEART, (PlayerEntity) entity, 10);

                                int ticks = nbt.getInt("ticks");
                                nbt.putInt("ticks", ticks + tickRate);

                                if (ticks > 20 * this.activationTimeSeconds) {

                                    nbt.putInt("ticks", 0);
                                    nbt.putBoolean("porting", false);

                                    teleportBack((PlayerEntity) entity, stack);

                                }
                            } else {
                                nbt.putInt("ticks", tickRate);
                            }

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void teleportBack(PlayerEntity player, ItemStack stack) {

        BlockPos pos = getLoc(stack);

        if (stack.hasTag() && stack.getTag().contains("dim") && pos != null) {
            pos = pos.up();
            String dim = stack.getTag().getString("dim");
            ResourceLocation res = new ResourceLocation(dim);
            DimensionType type = MapManager.getDimensionType(res);

            if (player.dimension != type) {
                PlayerUtils.changeDimension((ServerPlayerEntity) player, type, pos);

            }

            player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());

        } else {
            player.sendMessage(Chats.Not_attuned_to_any_altars.locName());

        }
    }

    public BlockPos getLoc(ItemStack stack) {

        if (stack.hasTag() && stack.getTag().contains("loc") && stack.getTag()
                .contains("dim")) {

            return BlockPos.fromLong(stack.getTag().getLong("loc"));
        }

        return null;
    }

    public void setLoc(ItemStack stack, BlockPos pos, DimensionType type) {

        ResourceLocation loc = DimensionType.getKey(type);

        if (loc != null) {
            stack.getTag().putLong("loc", pos.toLong());
            stack.getTag().putString("dim", loc.toString());
        }
    }

    public boolean distanceCanBeTeleported(PlayerEntity player, ItemStack stack) {

        BlockPos place = getLoc(stack);
        BlockPos current = player.getPosition();

        double distance = place.manhattanDistance(current);

        if (distance < this.blocksTeleported) {
            return true;
        }

        return false;

    }

    public boolean hasLoc(ItemStack stack) {
        return getLoc(stack) != null;
    }

    private int getRemainingUses(ItemStack stack) {

        if (stack.hasTag()) {
            CompoundNBT nbt = stack.getTag();

            if (nbt.contains("uses")) {
                return nbt.getInt("uses");
            }
        }
        return this.totalUses;

    }

    private void decreaseUses(ItemStack stack) {

        CompoundNBT nbt = stack.getTag();

        int left = this.totalUses;

        if (nbt.contains("uses")) {
            left = nbt.getInt("uses");
        }
        left--;

        nbt.putInt("uses", left);

        stack.setTag(nbt);

        if (left < 1) {
            stack.shrink(1);
        }

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        Tooltip.add("", tooltip);

        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(Words.Distance.locName()
                        .appendText(" " + this.blocksTeleported)
                        .appendText(" ")
                        .appendSibling(Words.Blocks.locName()
                                .appendText(". "))), tooltip);

        Tooltip.add(Styles.GREENCOMP()
                .appendSibling(Words.Uses.locName()
                        .appendText(": " + this.totalUses)
                        .appendText(" ")
                        .appendSibling(Words.Left.locName())
                        .appendText(": " + this.getRemainingUses(stack))), tooltip);

        Tooltip.add(Styles.REDCOMP()
                .appendSibling(Words.Activation_Time.locName()
                        .appendText(": " + this.activationTimeSeconds + " ")
                        .appendSibling(Words.Seconds.locName())), tooltip);

        if (getLoc(stack) != null) {
            Tooltip.add(Styles.GOLDCOMP()
                    .appendSibling(Words.Position.locName()
                            .appendText(": " + locTooltip(stack))), tooltip);

            Tooltip.add(Styles.BLUECOMP()
                    .appendSibling(new StringTextComponent(dimTooltip(stack))), tooltip);

        }

        Tooltip.add("", tooltip);

        tooltip.add(TooltipUtils.level(this.levelReq));

    }

    private String dimTooltip(ItemStack stack) {

        return stack.getTag().getString("dim");

    }

    private String locTooltip(ItemStack stack) {

        BlockPos pos = this.getLoc(stack);

        return "x: " + pos.getX() + " y: " + pos.getY() + " z: " + pos.getZ();

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    Hand hand) {

        if (world.isRemote == false) {

            ItemStack stack = player.getHeldItem(hand);

            if (stack.getItem() instanceof Hearthstone == false) {
                return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
            }

            Hearthstone item = (Hearthstone) stack.getItem();

            try {

                if (!stack.hasTag()) {
                    stack.setTag(new CompoundNBT());
                }

                if (item.hasLoc(stack) == false) {
                    player.sendMessage(Chats.Not_attuned_to_any_altars.locName());
                    stack.getTag().putBoolean("porting", false);
                    return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
                }

                if (item.distanceCanBeTeleported(player, stack) == false) {
                    player.sendMessage(Chats.Distance_too_high_to_teleport.locName());
                    stack.getTag().putBoolean("porting", false);
                    return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
                }

                if (Load.Unit(player).getLevel() < item.levelReq) {
                    player.sendMessage(Chats.You_are_too_low_level.locName());
                    stack.getTag().putBoolean("porting", false);
                    return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
                }

                item.decreaseUses(stack);

                stack.getTag().putBoolean("porting", true);
                stack.getTag().putLong("pos", player.getPosition().toLong());

                player.sendMessage(Chats.Teleport_started.locName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
    }

}
