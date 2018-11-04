package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;

import net.minecraft.util.text.TextFormatting;

public class Champion extends MobRarity {

	@Override
	public float StatMultiplier() {
		return 2;
	}

	@Override
	public String Name() {
		return "Champion";
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
		return 15000;
	}

	@Override
	public float LootMultiplier() {
		return 2.2F;
	}

	@Override
	public int MaxMobEffects() {
		return 1;
	}

	@Override
	public float ExpOnKill() {
		return 15;
	}

}
