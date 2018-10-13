package com.robertx22.database.rarities.general;

import com.robertx22.classes.MinMax;
import com.robertx22.gearitem.ItemRarity;

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
		return 2500;
	}

	@Override
	public int AffixChance() {
		return 50;
	}

	@Override
	public MinMax SecondaryStatsAmount() {
		return new MinMax(2, 3);
	}

	@Override
	public MinMax PrimaryStatsPercents() {
		return new MinMax(20, 75);
	}
}
