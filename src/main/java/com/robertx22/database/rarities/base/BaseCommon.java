package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

import net.minecraft.util.text.TextFormatting;

public abstract class BaseCommon implements Rarity {

    @Override
    public String GUID() {
	return "Common";
    }

    @Override
    public int Rank() {

	return 0;
    }

    @Override
    public String Color() {
	return TextFormatting.GRAY.toString();
    }

}
