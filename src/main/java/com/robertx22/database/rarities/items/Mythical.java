package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;

import net.minecraft.util.text.TextFormatting;

public class Mythical extends ItemRarity {

	public Mythical() {
	}

	@Override
	public String Name() {

		return "Mythical";
	}

	@Override
	public int Rank() {

		return 5;
	}

	@Override
	public String Color() {
		return TextFormatting.LIGHT_PURPLE.toString();
	}

	@Override
	public int Weight() {
		return 300;
	}

	@Override
	public int AffixChance() {
		return 100;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(3, 5);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(35, 100);
	}
}
