package com.robertx22.gearitem;

import java.util.HashMap;
import java.util.List;

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

	public abstract List<StatMod> ChaosStats();

	public abstract Item DefaultItem();

	public abstract HashMap<Integer, Item> ItemsForRarities();

	public abstract int Weight();

	public ItemStack GetItemForRarity(int rarityNum) {

		if (ItemsForRarities().containsKey(rarityNum)) {
			return new ItemStack(ItemsForRarities().get(rarityNum));
		}

		return new ItemStack(DefaultItem());

	}

}
