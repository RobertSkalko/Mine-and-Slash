package com.robertx22.generation;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class StatGen {

	public static int GenPercent(ItemRarity rarity) {

		return RandomUtils.RandomRange(rarity.StatPercents().Min, rarity.StatPercents().Max);
	}
}
