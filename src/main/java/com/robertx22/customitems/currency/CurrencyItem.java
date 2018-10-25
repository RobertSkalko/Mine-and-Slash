package com.robertx22.customitems.currency;

import java.util.HashSet;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.item.Item;

public class CurrencyItem extends Item implements IWeighted {

	public static HashSet<Item> ITEMS = new HashSet<Item>();

	public CurrencyItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxDamage(0);
		maxStackSize = 64;
		setCreativeTab(NewBlocks.MyModTab);

		ITEMS.add(this);

	}

	@Override
	public int Weight() {
		return 1000;
	}

}
