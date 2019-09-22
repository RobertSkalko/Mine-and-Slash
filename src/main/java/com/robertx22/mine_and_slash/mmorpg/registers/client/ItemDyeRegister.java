package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothBootsItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothChestItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothHelmetItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothPantsItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherBootsItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherChestItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherHelmetItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherPantsItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ItemDyeRegister {

    @SubscribeEvent
    public static void colorRegister(ColorHandlerEvent.Item evt) {

        List<IItemProvider> list = new ArrayList<>();

        // LEATHER
        list.addAll(LeatherHelmetItem.Items.values());
        list.addAll(LeatherPantsItem.Items.values());
        list.addAll(LeatherChestItem.Items.values());
        list.addAll(LeatherBootsItem.Items.values());

        //CLOTH
        list.addAll(ClothHelmetItem.Items.values());
        list.addAll(ClothPantsItem.Items.values());
        list.addAll(ClothChestItem.Items.values());
        list.addAll(ClothBootsItem.Items.values());

        evt.getItemColors()
                .register((stack, color) -> getColor(stack, color), list.toArray(new IItemProvider[]{}));

    }

    private static int getColor(ItemStack stack, int color) {
        return color > 0 ? -1 : ((IDyeableArmorItem) stack.getItem()).getColor(stack);
    }
}
