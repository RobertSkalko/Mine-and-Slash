package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;

import net.minecraft.util.text.TextFormatting;

public class Elite extends MobRarity {

	@Override
	public float StatMultiplier() {
		return 1.2F;
	}

	@Override
	public String Name() {
		return "Elite";
	}

	@Override
	public int Rank() {
		return 1;
	}

	@Override
	public String Color() {
		return TextFormatting.YELLOW.toString();
	}

	@Override
	public int Weight() {
		return 10000;
	}

	@Override
	public float LootMultiplier() {
		return 1.5F;
	}

	@Override
	public int MaxMobEffects() {
		return 1;
	}
}
