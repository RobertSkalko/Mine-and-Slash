package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;

import net.minecraft.util.text.TextFormatting;

public class Boss extends MobRarity {

	@Override
	public float StatMultiplier() {
		return 5;
	}

	@Override
	public String Name() {
		return "Boss";
	}

	@Override
	public int Rank() {
		return 4;
	}

	@Override
	public String Color() {
		return TextFormatting.GOLD.toString();
	}

	@Override
	public int Weight() {
		return 1000;
	}

	@Override
	public float LootMultiplier() {
		return 4F;
	}

	@Override
	public int MaxMobEffects() {
		return 2;
	}

	@Override
	public float ExpOnKill() {
		return 25;
	}

}
