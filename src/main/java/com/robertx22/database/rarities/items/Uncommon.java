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
		return 15;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(1, 1);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(5, 65);
	}

	@Override
	public int SetChance() {
		return 15;
	}

	@Override
	public MinMax SpellBasePercents() {
		return new MinMax(35, 60);
	}

	@Override
	public MinMax SpellScalingPercents() {
		return new MinMax(35, 60);
	}
}
