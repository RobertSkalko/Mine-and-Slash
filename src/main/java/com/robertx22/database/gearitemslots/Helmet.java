package com.robertx22.database.gearitemslots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.armor.ItemHelmet;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Helmet extends GearItemSlot {

	@Override
	public String Name() {
		return "Helmet";
	}

	@Override
	public List<Suffix> PossibleSuffixes() {
		return Arrays.asList(null);
	}

	@Override
	public List<Prefix> PossiblePrefixes() {
		return Arrays.asList(null);
	}

	@Override
	public List<StatMod> PrimaryStats() {
		return Arrays.asList(null);
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(null);
	}

	@Override
	public Item DefaultItem() {
		return ItemHelmet.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemHelmet.Items;
	}

	@Override
	public int Weight() {
		return 1000;
	}

}
