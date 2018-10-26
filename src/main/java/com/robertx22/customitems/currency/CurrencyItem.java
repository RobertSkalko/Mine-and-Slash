package com.robertx22.customitems.currency;

import java.util.HashSet;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CurrencyItem extends Item implements IWeighted {

	public static HashSet<Item> ITEMS = new HashSet<Item>();

	public static final CreativeTabs CurrencyTab = new CreativeTabs(Ref.NAME) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemChaosOrb.ITEM);
		}

	};

	public CurrencyItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxDamage(0);
		maxStackSize = 64;
		setCreativeTab(CurrencyTab);

		ITEMS.add(this);

	}

	@Override
	public int Weight() {
		return 1000;
	}

}
