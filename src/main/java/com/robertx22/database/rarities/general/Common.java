package com.robertx22.database.rarities.general;

import com.robertx22.classes.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Common extends Rarity {

	private static Common common = new Common();
	
	public Common() {}
	
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
		return 10000;
	}

}
