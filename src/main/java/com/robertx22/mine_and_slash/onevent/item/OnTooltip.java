package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.database.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.misc.ItemMapBackPortal;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnTooltip {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemTooltip(ItemTooltipEvent event) {

        buildDataTootltip(event);
        buildCurrencyEffectTooltip(event);

    }

    private static void buildDataTootltip(ItemTooltipEvent event) {

        try {
            if (Screen.hasControlDown()) {
                return;
            }

            if (event.getEntityPlayer() == null || event.getEntityPlayer().world == null || !event
                    .getEntityPlayer().world.isRemote) {
                return;
            }

            ItemStack stack;

            stack = event.getItemStack();

            UnitData unitdata = Load.Unit(event.getEntityPlayer());

            if (unitdata == null) {
                return;
            }

            Unit unit = unitdata.getUnit();

            if (unit == null) {
                return;
            }

            if (stack.getItem() instanceof ItemMapBackPortal) {

                try {
                    event.getToolTip()
                            .add(Styles.BLUECOMP()
                                    .appendSibling(Words.CurrentMapInfo.locName())
                                    .appendText(": "));

                    MapItemData map = Load.playerMapData(event.getEntityPlayer())
                            .getMap();
                    map.BuildTooltip(stack, event, unit, unitdata);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;

            }

            if (!stack.hasTag()) {

                return;
            }

            ICommonDataItem data = ICommonDataItem.load(stack);

            if (data != null) {
                data.BuildTooltip(stack, event, unit, unitdata);
            } else {
                if (stack.getItem().getRegistryName() != null) {
                    if (SlashRegistry.CompatibleItems()
                            .isRegistered(stack.getItem().getRegistryName().toString())) {

                        event.getToolTip()
                                .add(new StringTextComponent(Styles.RED + "Compatible Mine and Slash Item"));

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void buildCurrencyEffectTooltip(ItemTooltipEvent event) {

        if (event.getItemStack().getItem() instanceof ICurrencyItemEffect) {
            ICurrencyItemEffect currency = (ICurrencyItemEffect) event.getItemStack()
                    .getItem();
            currency.addToTooltip(event.getToolTip());
        }

    }

}
