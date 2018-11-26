package com.robertx22.customitems.currency;

import java.util.HashSet;
import java.util.List;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;

import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

public class CurrencyItem extends Item implements IWeighted {

	public static HashSet<Item> ITEMS = new HashSet<Item>();

	public CurrencyItem(String name) {
		setMaxDamage(0);
		maxStackSize = 64;
		setCreativeTab(CreativeTabList.CurrencyTab);
		RegisterItemUtils.RegisterItemName(this, name);

		ITEMS.add(this);

	}

	public void TooltipQuote(List<String> tooltip, String quote) {
		tooltip.add(TextFormatting.GREEN + "'" + quote + "'");
	}

	@Override
	public int Weight() {
		return this.UncommonWeight;
	}

}
