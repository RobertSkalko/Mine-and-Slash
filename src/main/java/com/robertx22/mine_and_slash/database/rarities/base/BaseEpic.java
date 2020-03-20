package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.RGB;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseEpic implements Rarity {
    @Override
    public int colorInt() {
        return 5592575;
    }

    @Override
    public String GUID() {

        return "Epic";
    }

    @Override
    public int Rank() {
        return IRarity.Epic;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(75, 95);
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
    public RGB getRGBColor() {
        return new RGB(204, 0, 255);
    }

    @Override
    public String locNameForLangFile() {
        return "Epic";
    }
}