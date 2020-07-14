package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseCommon implements Rarity {

    @Override
    public String GUID() {
        return "Common";
    }

    @Override
    public int Rank() {
        return IRarity.Common;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.GRAY;
    }

    @Override
    public String locNameForLangFile() {
        return "Common";
    }
}
