package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;

import net.minecraft.util.text.TextFormatting;

public class Epic extends ItemRarity {
	public Epic() {
	}

	@Override
	public String Name() {

		return "Epic";
	}

	@Override
	public int Rank() {

		return 3;
	}

	@Override
	public String Color() {
		return TextFormatting.BLUE.toString();
	}

	@Override
	public int Weight() {
		return 3000;
	}

	@Override
	public int AffixChance() {
		return 60;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(2, 3);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(20, 75);
	}
}
