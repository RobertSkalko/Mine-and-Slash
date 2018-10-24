package com.robertx22.database.gearitemslots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.baubles.ItemBracelet;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.lists.Prefixes;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.database.stats.mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Bracelet extends GearItemSlot {

	@Override
	public String Name() {
		return "Bracelet";
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
		return Arrays.asList(new FireResistFlat(), new ThunderResistFlat(), new WaterResistFlat(),
				new NatureResistFlat());
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new FireResistFlat(), new ThunderResistFlat(), new WaterResistFlat(),
				new NatureResistFlat());
	}

	@Override
	public Item DefaultItem() {
		return ItemBracelet.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemBracelet.Items;
	}

}