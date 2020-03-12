package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class TeleportScrollItem extends Item implements IShapedRecipe {

    public TeleportScrollItem() {
        super(new Properties().maxStackSize(64)
            .group(CreativeTabs.MyModTab));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        if (!world.isRemote) {
            try {

                ItemStack tome = player.getHeldItem(hand);

                if (tome.getItem() instanceof TeleportScrollItem) {

                    if (WorldUtils.isMapWorldClass(world)) {
                        Load.playerMapData(player)
                            .teleportPlayerBack(player);
                        tome.shrink(1);
                        return new ActionResult<ItemStack>(ActionResultType.CONSUME, tome);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Words.RightClickToTeleport.locName());
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.TELEPORT_SCROLL.get(), 4)
            .key('b', Items.PAPER)
            .key('v', Items.LEATHER)
            .key('o', ItemOre.ItemOres.get(IRarity.Epic))
            .patternLine("oo")
            .patternLine("bv")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(1));
    }

}
