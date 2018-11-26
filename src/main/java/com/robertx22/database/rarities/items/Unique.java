package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;

import net.minecraft.util.text.TextFormatting;

public class Unique extends ItemRarity {

	public Unique() {
	}

	@Override
	public String Name() {

		return "Unique";
	}

	@Override
	public int Rank() {

		return -1;
	}

	@Override
	public String Color() {
		return TextFormatting.YELLOW.toString();
	}

	@Override
	public int Weight() {
		return 0;
	}

	@Override
	public int AffixChance() {
		return 25;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(0, 0);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(25, 100);
	}

	@Override
	public int SetChance() {
		return 0;
	}

	@Override
	public MinMax SpellBasePercents() {
		return new MinMax(0, 1);
	}

	@Override
	public MinMax SpellScalingPercents() {
		return new MinMax(0, 1);
	}

}