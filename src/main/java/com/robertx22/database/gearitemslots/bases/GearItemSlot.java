package com.robertx22.database.gearitemslots.bases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.db_lists.StatMods;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class GearItemSlot implements IWeighted {

	public abstract String Name();

	public abstract List<Suffix> PossibleSuffixes();

	public abstract List<Prefix> PossiblePrefixes();

	public abstract List<StatMod> PrimaryStats();

	public abstract List<StatMod> PossibleSecondaryStats();

	public abstract Item DefaultItem();

	public abstract HashMap<Integer, Item> ItemsForRarities();

	public int Weight() {
		return 1000;
	}

	public ItemStack GetItemForRarity(int rarityNum) {

		if (ItemsForRarities().containsKey(rarityNum)) {
			return new ItemStack(ItemsForRarities().get(rarityNum));
		}

		return new ItemStack(DefaultItem());

	}

	public List<StatMod> ChaosStats() {

		List<StatMod> list = new ArrayList<StatMod>();

		for (StatMod mod : StatMods.All.values()) {
			if (mod instanceof BaseTraitMod) {
				list.add(mod);
			}
		}

		return list;
	}

}
