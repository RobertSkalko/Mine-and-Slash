package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

import net.minecraft.util.text.TextFormatting;

public abstract class BaseUnique implements Rarity {

    @Override
    public String GUID() {

	return "Unique";
    }

    @Override
    public int Rank() {

	return -1;
    }

    @Override
    public String Color() {
	return TextFormatting.YELLOW.toString();
    }

    @Override
    public int Weight() {
	return 0;
    }

}