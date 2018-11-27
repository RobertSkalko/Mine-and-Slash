package com.robertx22.database.gearitemslots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.weapons.ItemStaff;
import com.robertx22.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Staff extends BaseWeapon {

	@Override
	public String Name() {
		return "Staff";
	}

	@Override
	public Item DefaultItem() {
		return ItemStaff.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemStaff.Items;
	}

	@Override
	public int Weight() {
		return 1500;
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat(), new ManaOnHitFlat());
	}

}