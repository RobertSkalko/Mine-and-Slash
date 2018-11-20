package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;

import net.minecraft.util.text.TextFormatting;

public class UncommonMap extends MapRarity {

	@Override
	public MinMax AffixAmount() {
		return new MinMax(2, 3);
	}

	@Override
	public MinMax StatPercents() {
		return new MinMax(15, 35);
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
		return this.CommonWeight;
	}

}
