package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherBootsItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherChestItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherHelmetItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherPantsItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemDyeRegister {

    @SubscribeEvent
    public static void colorRegister(ColorHandlerEvent.Item evt) {

        List<IItemProvider> list = new ArrayList<>();
        list.addAll(LeatherHelmetItem.Items.values());
        list.addAll(LeatherPantsItem.Items.values());
        list.addAll(LeatherChestItem.Items.values());
        list.addAll(LeatherBootsItem.Items.values());

        evt.getItemColors()
                .register((stack, color) -> getColor(stack, color), list.toArray(new IItemProvider[]{}));

    }

    private static int getColor(ItemStack stack, int color) {
        return color > 0 ? -1 : ((IDyeableArmorItem) stack.getItem()).getColor(stack);
    }
}
