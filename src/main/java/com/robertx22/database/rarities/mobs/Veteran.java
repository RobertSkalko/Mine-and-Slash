package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;

import net.minecraft.util.text.TextFormatting;

public class Veteran extends MobRarity {

	@Override
	public float StatMultiplier() {
		return 1.4F;
	}

	@Override
	public String Name() {
		return "Veteran";
	}

	@Override
	public int Rank() {
		return 2;
	}

	@Override
	public String Color() {
		return TextFormatting.GREEN.toString();
	}

	@Override
	public int Weight() {
		return 7500;
	}

	@Override
	public float LootMultiplier() {
		return 1.75F;
	}

	@Override
	public int MaxMobEffects() {
		return 2;
	}
}
