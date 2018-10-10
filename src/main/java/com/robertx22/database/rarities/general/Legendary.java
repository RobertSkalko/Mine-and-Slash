package com.robertx22.database.rarities.general;

import com.robertx22.constants.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Legendary extends Rarity {

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

}
