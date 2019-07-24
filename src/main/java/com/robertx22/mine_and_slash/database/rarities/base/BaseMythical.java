package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
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
    public TextFormatting textFormatColor() {
        return TextFormatting.LIGHT_PURPLE;
    }

    @Override
    public String Color() {
        return TextFormatting.LIGHT_PURPLE.toString();
    }

    @Override
    public Elements.RGB getRGBColor() {
        return new Elements.RGB(204, 0, 255);
    }

    @Override
    public String locNameForLangFile() {
        return "Mythical";
    }
}
