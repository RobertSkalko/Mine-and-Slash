package com.robertx22.database.rarities.general;

import com.robertx22.gearitem.Rarity;

import net.minecraft.util.text.TextFormatting;

public class Mythical extends Rarity {
	
	public Mythical() {}
	
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
		return 200;
	}
}
