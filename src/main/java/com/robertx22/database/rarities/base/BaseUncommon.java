package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

import net.minecraft.util.text.TextFormatting;

public abstract class BaseUncommon implements Rarity {

    @Override
    public String GUID() {
	return "Uncommon";
    }

    @Override
    public int Rank() {
	return 1;
    }

    @Override
    public String Color() {

	return TextFormatting.GREEN.toString();
    }

}
