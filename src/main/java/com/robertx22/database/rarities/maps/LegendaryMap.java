package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;

import net.minecraft.util.text.TextFormatting;

public class LegendaryMap extends MapRarity {

	@Override
	public MinMax AffixAmount() {
		return new MinMax(5, 6);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(40, 70);
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
		return this.LegendaryWeight;
	}

}