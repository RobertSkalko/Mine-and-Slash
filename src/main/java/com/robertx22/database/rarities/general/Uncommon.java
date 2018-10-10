package com.robertx22.database.rarities.general;

import com.robertx22.classes.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Uncommon extends Rarity {

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
		return 7500;
	}
}
