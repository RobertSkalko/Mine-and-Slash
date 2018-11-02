package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;

import net.minecraft.util.text.TextFormatting;

public class WorldBoss extends MobRarity {

	@Override
	public float StatMultiplier() {
		return 10;
	}

	@Override
	public String Name() {
		return "World Boss";
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
		return 250;
	}

	@Override
	public float LootMultiplier() {
		return 8F;
	}

	@Override
	public int MaxMobEffects() {
		return 3;
	}
}
