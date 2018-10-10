package com.robertx22.database.rarities.general;

import com.robertx22.constants.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Rare  extends Rarity {

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


}
