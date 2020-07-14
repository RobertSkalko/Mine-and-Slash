package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseMagical implements Rarity {

    @Override
    public String GUID() {
        return "magical";
    }

    @Override
    public int Rank() {
        return IRarity.Magical;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.GREEN;
    }

    @Override
    public String locNameForLangFile() {
        return "Magical";
    }
}
