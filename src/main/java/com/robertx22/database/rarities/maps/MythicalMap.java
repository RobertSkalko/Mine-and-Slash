package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;

import net.minecraft.util.text.TextFormatting;

public class MythicalMap extends MapRarity {

	@Override
	public MinMax AffixAmount() {
		return new MinMax(6, 7);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(50, 100);
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
		return this.MythicWeight;
	}

}