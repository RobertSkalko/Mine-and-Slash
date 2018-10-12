package com.robertx22.database.gearitemslots;

import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.MyItems;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;
import scala.actors.threadpool.Arrays;

public class Boots extends GearItemSlot {

	@Override
	public String Name() {
		return "Boots";
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
		return MyItems.magic_boots;
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return null;
	}

	@Override
	public int Weight() {
		return 1000;
	}

}
