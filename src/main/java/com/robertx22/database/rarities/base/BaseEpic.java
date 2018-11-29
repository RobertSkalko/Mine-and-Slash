package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

import net.minecraft.util.text.TextFormatting;

public abstract class BaseEpic implements Rarity {

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
	return 3000;
    }

}