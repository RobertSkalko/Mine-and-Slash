package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;

import net.minecraft.util.text.TextFormatting;

public class Common extends ItemRarity {

	private static Common common = new Common();

	public Common() {
	}

	@Override
	public String Name() {

		return "Common";
	}

	@Override
	public int Rank() {

		return 0;
	}

	@Override
	public String Color() {
		return TextFormatting.GRAY.toString();
	}

	@Override
	public int Weight() {
		return 25000;
	}

	@Override
	public int AffixChance() {
		return 10;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(0, 1);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(3, 60);
	}

	@Override
	public int SetChance() {
		return 5;
	}

}
