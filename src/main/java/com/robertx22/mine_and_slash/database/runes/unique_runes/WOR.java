package com.robertx22.mine_and_slash.database.runes.unique_runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseUniqueRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalFocusFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.List;

public class WOR extends BaseUniqueRune {

    @Override
    public List<StatMod> mods() {
        return new ElementalFocusFlat(Elements.Physical).allSingleElementVariations();
    }

    @Override
    public String name() {
        return "WOR";
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

}