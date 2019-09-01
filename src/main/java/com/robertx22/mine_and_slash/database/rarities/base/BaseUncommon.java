package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
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

    @Override
    public TextFormatting textFormatColor() {
        return TextFormatting.GREEN;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(55, 65);
    }

    @Override
    public Elements.RGB getRGBColor() {
        return new Elements.RGB(102, 255, 102);
    }

    @Override
    public String locNameForLangFile() {
        return "Uncommon";
    }
}
