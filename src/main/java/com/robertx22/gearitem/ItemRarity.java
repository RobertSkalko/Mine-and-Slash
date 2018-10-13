package com.robertx22.gearitem;

import com.robertx22.classes.MinMax;

public abstract class ItemRarity extends Rarity {
	public abstract int AffixChance();

	public abstract MinMax SecondaryStatsAmount();

	public abstract MinMax PrimaryStatsPercents();
}
