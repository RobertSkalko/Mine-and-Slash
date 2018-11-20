package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;

import net.minecraft.util.text.TextFormatting;

public class EpicMap extends MapRarity {

	@Override
	public MinMax AffixAmount() {
		return new MinMax(4, 5);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(30, 55);
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
		return this.EpicWeight;
	}

}