package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseUnique implements Rarity {
    @Override
    public String GUID() {

        return "unique";
    }

    @Override
    public int Rank() {

        return IRarity.Unique;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.RED;
    }

    @Override
    public int Weight() {
        return 0;
    }

    @Override
    public String locNameForLangFile() {
        return "Unique";
    }

}