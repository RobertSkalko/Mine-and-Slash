package com.robertx22.database.gearitemslots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.MyItems;
import com.robertx22.database.lists.Prefixes;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.database.stats.mods.flat.ArmorFlat;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Boots extends GearItemSlot {

	@Override
	public String Name() {
		return "Boots";
	}

	@Override
	public List<Suffix> PossibleSuffixes() {
		return new ArrayList<Suffix>(Suffixes.All.values());
	}

	@Override
	public List<Prefix> PossiblePrefixes() {
		return new ArrayList<Prefix>(Prefixes.All.values());
	}

	@Override
	public List<StatMod> PrimaryStats() {
		return Arrays.asList(new HealthFlat());
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new ArmorFlat());
	}

	@Override
	public Item DefaultItem() {
		return MyItems.magic_boots;
	}

	private HashMap<Integer, Item> perRarity = new HashMap<Integer, Item>() {
		{
			{
				put(0, MyItems.magic_boots);
			}
		}
	};

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return perRarity;
	}

	@Override
	public int Weight() {
		return 1555000;
	}

}
