package com.robertx22.mine_and_slash.gui.trader;

import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

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

            HashMap<Rarity, Integer> map = ISellPrice.commonOresToExactListOfHigherRarities(totalCost);

            int x = event.getX() + 4;
            int y = event.getY() + event.getHeight() - 16;

            for (Map.Entry<Rarity, Integer> entry : map.entrySet()) {
                ItemStack mainCostStack = new ItemStack(ItemOre.ItemOres.get(entry.getKey()
                    .Rank()));
                mainCostStack.setCount(entry.getValue());

                Minecraft mc = Minecraft.getInstance();

                mc.getItemRenderer()
                    .renderItemAndEffectIntoGUI(mc.player, mainCostStack, x, y);

                mc.getItemRenderer()
                    .renderItemOverlays(mc.fontRenderer, mainCostStack, x, y);

                x += 18;

            }

        }

    }

}
