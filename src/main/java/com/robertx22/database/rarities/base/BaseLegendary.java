package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

import net.minecraft.util.text.TextFormatting;

public abstract class BaseLegendary implements Rarity {

    @Override
    public String GUID() {

	return "Legendary";
    }

    @Override
    public int Rank() {

	return 4;
    }

    @Override
    public String Color() {
	return TextFormatting.GOLD.toString();
    }

}
