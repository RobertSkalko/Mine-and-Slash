package com.robertx22.database.rarities.general;

import com.robertx22.classes.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Rare extends Rarity {
	public Rare() {}
	
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
		return 5000;
	}
}
