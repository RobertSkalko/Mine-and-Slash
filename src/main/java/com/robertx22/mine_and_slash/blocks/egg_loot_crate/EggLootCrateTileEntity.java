package com.robertx22.mine_and_slash.blocks.egg_loot_crate;

import com.robertx22.mine_and_slash.blocks.conditions.IConditionalLootCrate;
import com.robertx22.mine_and_slash.blocks.conditions.LootCrateCondition;
import com.robertx22.mine_and_slash.blocks.conditions.NoMobAroundCondition;
import com.robertx22.mine_and_slash.database.rarities.items.MythicalItem;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.saveclasses.PlayerOncePerMapData;
import com.robertx22.mine_and_slash.uncommon.datasaving.PlayerOncePerMap;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.item.FireworkRocketEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class EggLootCrateTileEntity extends TileEntity implements ITickableTileEntity, IConditionalLootCrate {

    public static final String dataLoc = "PlayerOncePerMapData";
    public static final String isdroppingloc = "isdroppingloc";
    public static final String droplootticksloc = "droplootticksloc";
    public static final String timestodroploc = "timesToDrop";

    PlayerOncePerMapData data = new PlayerOncePerMapData();
    PlayerEntity player;

    int timesToDrop = getTimesToDrop();
    public boolean isDroppingLoot = false;
    int dropLootTicks = 0;

    public EggLootCrateTileEntity() {
        super(BlockRegister.EGG_LOOT_CRATE);
    }

    public void firework() {

        // turns invisible for some reason?
        FireworkRocketEntity firework = new FireworkRocketEntity(world, pos.getX(), pos.getY() + 2, pos
                .getZ(), ItemStack.EMPTY);
        firework.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
        firework.setInvulnerable(true);
        WorldUtils.spawnEntity(world, firework);

    }

    public void dropLoot(PlayerEntity player) {

        timesToDrop--;

        if (player != null) {

            List<ItemStack> loot = MasterLootGen.generateLoot(new LootInfo(player).setMinimum(1));

            for (ItemStack stack : loot) {
                WorldUtils.spawnEntity(world, new ItemEntity(world, pos.getX(), pos.getY() + 1, pos
                        .getZ(), stack));
            }
            if (loot.size() > 0) {
                firework();
            }
        }

    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        CompoundNBT datanbt = new CompoundNBT();
        PlayerOncePerMap.Save(datanbt, data);
        nbt.put(dataLoc, datanbt);
        nbt.putInt(timestodroploc, this.timesToDrop);
        nbt.putBoolean(isdroppingloc, isDroppingLoot);
        nbt.putInt(droplootticksloc, dropLootTicks);

        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        this.data = PlayerOncePerMap.Load(nbt.getCompound(dataLoc));
        if (data == null) {
            data = new PlayerOncePerMapData();
        }

        this.timesToDrop = nbt.getInt(timestodroploc);
        this.isDroppingLoot = nbt.getBoolean(isdroppingloc);
        this.dropLootTicks = nbt.getInt(droplootticksloc);

    }

    public void activateDrops(PlayerEntity player) {
        data.add(player);
        this.player = player;
        this.isDroppingLoot = true;
        this.timesToDrop = this.getTimesToDrop();

    }

    public int getTimesToDrop() {
        return 5;
    }

    public void reset() {

        isDroppingLoot = false;
        this.timesToDrop = getTimesToDrop();

    }

    public boolean finished() {
        return timesToDrop < 1;
    }

    private Rarity getRarity() {
        return new MythicalItem();
    }

    @Override
    public void tick() {

        if (world == null || pos == null) {
            return;
        }
        if (world.isRemote) {
            return;
        }

        if (isDroppingLoot) {

            if (finished()) {
                reset();
            }

            ElementalParticleUtils.SpawnAoeParticle(getRarity(), world, pos.getX() + 0.5, pos
                    .getY() + 0.5, pos.getZ() + 0.5, 1.3F, 10); // 0.5 is cus blockpos truncates the values so it ends at the edges instead of center

            dropLootTicks++;

            if (timesToDrop > 0) {
                if (dropLootTicks > 20) {
                    dropLootTicks = 0;
                    this.dropLoot(player);
                }
            }
        }

    }

    public void tryActivate(PlayerEntity player) {

        if (data == null) {
            data = new PlayerOncePerMapData();
        }

        if (condition().canOpenCrate(player)) {

            if (data.canDo(player)) {

                if (isDroppingLoot) {
                    player.sendMessage(new StringTextComponent("This crate is currently being used."));
                } else {
                    activateDrops(player);
                }

            } else {
                player.sendMessage(new StringTextComponent("You have already used this block. Come again next map!"));
            }

        } else {
            player.sendMessage(condition().tellCondition());
        }

    }

    @Override
    public LootCrateCondition condition() {
        return new NoMobAroundCondition(4);
    }
}
