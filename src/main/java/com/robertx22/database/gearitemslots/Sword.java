package com.robertx22.database.gearitemslots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.weapons.ItemSword;
import com.robertx22.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.database.stats.mods.percent.CriticalDamagePercent;
import com.robertx22.database.stats.mods.percent.CriticalHitPercent;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Sword extends BaseWeapon {

	@Override
	public String Name() {
		return "Sword";
	}

	@Override
	public Item DefaultItem() {
		return ItemSword.Items.get(0);
	}

	@Override
	public List<StatMod> slotTypeStats() {
		return Arrays.asList(new CriticalHitPercent(), new CriticalDamagePercent());
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemSword.Items;
	}

	@Override
	public int Weight() {
		return 1500;
	}

}
