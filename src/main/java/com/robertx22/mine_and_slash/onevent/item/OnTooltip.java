package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
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

        if (Screen.hasControlDown()) {
            return;
        }

        if (event.getEntityPlayer() == null || event.getEntityPlayer().world == null || !event
                .getEntityPlayer().world.isRemote) {
            return;
        }

        ItemStack stack;

        stack = event.getItemStack();

        if (!stack.hasTag()) {

            return;
        }

        UnitData unitdata = Load.Unit(event.getEntityPlayer());

        if (unitdata == null) {
            return;
        }

        Unit unit = unitdata.getUnit();

        if (unit == null) {
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

    }

}
