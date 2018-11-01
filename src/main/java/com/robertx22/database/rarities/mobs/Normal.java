package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;

import net.minecraft.util.text.TextFormatting;

public class Normal extends MobRarity {

	@Override
	public float StatMultiplier() {
		return 1;
	}

	@Override
	public String Name() {
		return "Normal";
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
		return 15000;
	}

	@Override
	public float LootMultiplier() {
		return 1;
	}

	@Override
	public int MaxMobEffects() {
		return 0;
	}

}
