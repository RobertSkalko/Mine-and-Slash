package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

import net.minecraft.util.text.TextFormatting;

public abstract class BaseMythical implements Rarity {

    @Override
    public String GUID() {

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

}
