package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseRare implements Rarity {

    @Override
    public String GUID() {

        return "Rare";
    }

    @Override
    public int Rank() {

        return 2;
    }

    @Override
    public TextFormatting textFormatColor() {
        return TextFormatting.YELLOW;
    }

    @Override
    public String Color() {
        return TextFormatting.YELLOW.toString();
    }

    @Override
    public Elements.RGB getRGBColor() {
        return new Elements.RGB(255, 255, 102);
    }

    @Override
    public String locNameForLangFile() {
        return "Rare";
    }
}
