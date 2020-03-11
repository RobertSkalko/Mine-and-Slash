package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class IdentifyTomeItem extends Item implements IShapedRecipe {
    public IdentifyTomeItem() {
        super(new Properties().maxStackSize(64)
            .group(CreativeTabs.MyModTab));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        if (!world.isRemote) {
            try {

                ItemStack tome = player.getHeldItem(hand);

                if (tome.getItem() instanceof IdentifyTomeItem) {
                    for (ItemStack x : player.inventory.mainInventory) {
                        if (tryIdentify(x, tome)) {
                            spawnEffects(player);
                            return new ActionResult<ItemStack>(ActionResultType.CONSUME, tome);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    public boolean tryIdentify(ItemStack stack, ItemStack tome) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null) {
            if (!gear.isIdentified()) {

                gear.setIdentified(true);

                Gear.Save(stack, gear);

                tome.shrink(1);

                return true;
            }

        }
        return false;
    }

    private void spawnEffects(LivingEntity en) {

        ParticleEnum.sendToClients(en, new ParticlePacketData(en.getPositionVector()
            .add(0, 1, 0), ParticleEnum.AOE).radius(1)
            .type(ParticleTypes.ENCHANT)
            .amount(100)
            .motion(new Vec3d(0, 0, 0)));
        SoundUtils.playSound(en, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Words.RightClickToIdentifyFirst.locName());
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.IDENTIFY_TOME.get(), 16)
            .key('b', Items.BOOK)
            .key('v', Items.COAL)
            .key('o', ItemOre.ItemOres.get(IRarity.Rare))
            .patternLine("oo")
            .patternLine("bv")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(1));
    }

}
