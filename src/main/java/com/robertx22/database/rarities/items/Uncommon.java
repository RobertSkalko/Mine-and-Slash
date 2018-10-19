package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;

import net.minecraft.util.text.TextFormatting;

public class Uncommon extends ItemRarity {
	public Uncommon() {
	}

	@Override
	public String Name() {
		return "Uncommon";
	}

	@Override
	public int Rank() {
		return 1;
	}

	@Override
	public String Color() {

		return TextFormatting.GREEN.toString();
	}

	@Override
	public int Weight() {
		return 20000;
	}

	@Override
	public int AffixChance() {
		return 10;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(1, 1);
	}

	@Override
	public MinMax PrimaryStatsPercents() {
		return new MinMax(5, 65);
	}
}
