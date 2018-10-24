package com.robertx22.database.gearitemslots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.baubles.ItemNecklace;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.lists.Prefixes;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.database.stats.mods.flat.elemental.dmg.FireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.NatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.ThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.WaterDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Necklace extends GearItemSlot {

	@Override
	public String Name() {
		return "Necklace";
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
		return Arrays.asList(new FireDamageFlat(), new ThunderDamageFlat(), new WaterDamageFlat(),
				new NatureDamageFlat());
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new FireResistFlat(), new ThunderResistFlat(), new WaterResistFlat(),
				new NatureResistFlat());
	}

	@Override
	public Item DefaultItem() {
		return ItemNecklace.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemNecklace.Items;
	}

}
