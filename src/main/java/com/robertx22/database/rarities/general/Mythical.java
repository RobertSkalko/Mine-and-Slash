package com.robertx22.database.rarities.general;

import com.robertx22.constants.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Mythical extends Rarity {

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

}
