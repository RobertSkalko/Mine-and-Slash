package com.robertx22.customitems.gearitems.baubles;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseBaublesItem;

import baubles.api.BaubleType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRing extends BaseBaublesItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemRing() {

    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
	return BaubleType.RING;
    }

}
