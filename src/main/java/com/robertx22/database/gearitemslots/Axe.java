package com.robertx22.database.gearitemslots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.weapons.ItemAxe;
import com.robertx22.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.percent.CriticalHitPercent;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Axe extends BaseWeapon {

	@Override
	public String Name() {
		return "Axe";
	}

	@Override
	public Item DefaultItem() {
		return ItemAxe.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemAxe.Items;
	}

	@Override
	public int Weight() {
		return 1000;
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat(), new CriticalHitPercent());
	}

}