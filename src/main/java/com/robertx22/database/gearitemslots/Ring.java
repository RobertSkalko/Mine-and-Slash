package com.robertx22.database.gearitemslots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.baubles.ItemRing;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.lists.Prefixes;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.database.stats.mods.flat.elemental.dmg.FireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.NatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.ThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.WaterDamageFlat;
import com.robertx22.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Ring extends GearItemSlot {

	@Override
	public String Name() {
		return "Ring";
	}

	@Override
	public List<Suffix> PossibleSuffixes() {
		return new ArrayList<Suffix>(Suffixes.Jewerly);
	}

	@Override
	public List<Prefix> PossiblePrefixes() {
		return new ArrayList<Prefix>(Prefixes.Jewerly);
	}

	@Override
	public List<StatMod> PrimaryStats() {
		return Arrays.asList(new FireDamageFlat(), new ThunderDamageFlat(), new WaterDamageFlat(),
				new NatureDamageFlat());
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new EnergyRegenFlat(), new ManaRegenFlat(), new ManaFlat());
	}

	@Override
	public Item DefaultItem() {
		return ItemRing.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemRing.Items;
	}

	@Override
	public int Weight() {
		return 1500;
	}

}
