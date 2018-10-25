package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;

import net.minecraft.util.text.TextFormatting;

public class Rare extends ItemRarity {
	public Rare() {
	}

	@Override
	public String Name() {

		return "Rare";
	}

	@Override
	public int Rank() {

		return 2;
	}

	@Override
	public String Color() {
		return TextFormatting.YELLOW.toString();
	}

	@Override
	public int Weight() {
		return 5000;
	}

	@Override
	public int AffixChance() {
		return 20;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(1, 2);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(15, 70);
	}
}
