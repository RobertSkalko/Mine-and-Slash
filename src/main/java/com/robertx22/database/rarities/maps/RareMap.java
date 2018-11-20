package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;

import net.minecraft.util.text.TextFormatting;

public class RareMap extends MapRarity {

	@Override
	public MinMax AffixAmount() {
		return new MinMax(3, 4);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(20, 45);
	}

	@Override
	public String Name() {
		return "Rare";
	}

	@Override
	public int Rank() {
		return 2;
	}

	@Override
	public String Color() {
		return TextFormatting.YELLOW.toString();
	}

	@Override
	public int Weight() {
		return this.RareWeight;
	}

}
