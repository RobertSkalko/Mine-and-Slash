package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

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
                        GearItemData gear = Gear.Load(x);

                        if (gear != null) {

                            if (!gear.isIdentified()) {

                                gear.setIdentified(true);

                                Gear.Save(x, gear);

                                tome.shrink(1);

                                return new ActionResult<ItemStack>(ActionResultType.CONSUME, tome);
                            }

                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.IDENTIFY_TOME.get())
            .key('b', Items.BOOK)
            .key('v', Items.REDSTONE)
            .key('o', ItemOre.ItemOres.get(IRarity.Common))
            .patternLine("oo")
            .patternLine("bv")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(1));
    }

}
