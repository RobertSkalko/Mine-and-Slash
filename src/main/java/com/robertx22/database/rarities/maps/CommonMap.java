package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;

import net.minecraft.util.text.TextFormatting;

public class CommonMap extends MapRarity {

	@Override
	public MinMax AffixAmount() {
		return new MinMax(1, 2);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(10, 25);
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
		return this.CommonWeight;
	}

}
