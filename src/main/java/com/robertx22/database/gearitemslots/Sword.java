package com.robertx22.database.gearitemslots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.weapons.ItemSword;
import com.robertx22.database.lists.Prefixes;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.database.stats.mods.flat.DamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.FireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.NatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.ThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.WaterDamageFlat;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Sword extends GearItemSlot {

	@Override
	public String Name() {
		return "Sword";
	}

	@Override
	public List<Suffix> PossibleSuffixes() {
		return Suffixes.Weapon;
	}

	@Override
	public List<Prefix> PossiblePrefixes() {
		return Prefixes.Weapon;
	}

	@Override
	public List<StatMod> PrimaryStats() {
		return Arrays.asList(new DamageFlat());
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new FireDamageFlat(), new WaterDamageFlat(), new ThunderDamageFlat(),
				new NatureDamageFlat());
	}

	@Override
	public Item DefaultItem() {
		return ItemSword.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemSword.Items;
	}

	@Override
	public int Weight() {
		return 1000;
	}

	@Override
	public List<StatMod> ChaosStats() {
		return null;

	}
}
