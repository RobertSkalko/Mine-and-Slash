package com.robertx22.mine_and_slash.gui.trader;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TraderTooltipEvent {

    @SubscribeEvent
    public static void pre(RenderTooltipEvent.Pre event) {

        //  event.getLines()
        //     .add("");

    }

    @SubscribeEvent
    public static void post(RenderTooltipEvent.PostBackground event) {

        ItemStack stack = event.getStack();

        if (stack.isEmpty()) {
            return;
        }

        if (ISellPrice.hasPrice(stack)) {

            int totalCost = ISellPrice.getSavedPriceInCommonOres(stack);

            ItemStack mainCostStack = ISellPrice.getHighestRarityStackFromCommons(totalCost);

            //int leftover = ISellPrice.rarityOresToCommons(mainCostStack.getCount());

            int x = event.getX() + 4;
            int y = event.getY() + event.getHeight() - 16;

            Minecraft mc = Minecraft.getInstance();

            mc.getItemRenderer()
                .renderItemAndEffectIntoGUI(mc.player, mainCostStack, x, y);

            mc.getItemRenderer()
                .renderItemOverlays(mc.fontRenderer, mainCostStack, x, y);

        }

    }

}
