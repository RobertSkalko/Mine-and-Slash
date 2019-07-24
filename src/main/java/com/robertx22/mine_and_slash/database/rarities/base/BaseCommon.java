package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements.RGB;
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
    public TextFormatting textFormatColor() {
        return TextFormatting.GRAY;
    }

    @Override
    public String Color() {
        return TextFormatting.GRAY.toString();
    }

    @Override
    public RGB getRGBColor() {
        return new RGB(128, 128, 128);
    }

    @Override
    public String locNameForLangFile() {
        return "Common";
    }
}
