package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;

import net.minecraft.util.text.TextFormatting;

public class Legendary extends ItemRarity {
	public Legendary() {
	}

	@Override
	public String Name() {

		return "Legendary";
	}

	@Override
	public int Rank() {

		return 4;
	}

	@Override
	public String Color() {
		return TextFormatting.GOLD.toString();
	}

	@Override
	public int Weight() {
		return 1250;
	}

	@Override
	public int AffixChance() {
		return 80;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(2, 4);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(15, 90);
	}
}
