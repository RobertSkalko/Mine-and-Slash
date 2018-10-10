package com.robertx22.database.rarities.general;

import com.robertx22.classes.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Epic extends Rarity {

	@Override
	public String Name() {

		return "Epic";
	}

	@Override
	public int Rank() {

		return 3;
	}

	@Override
	public String Color() {
		return TextFormatting.BLUE.toString();
	}
	@Override
	public int Weight() {		
		return 2500;
	}
}
