package com.robertx22.mine_and_slash.database.items.runes.unique_runes;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.List;

public class QARItem extends BaseUniqueRuneItem {

    @Override
    public List<StatMod> mods() {
        return new ElementalAffinityFlat(Elements.Physical).getAllSingleElementVariations();
    }

    @Override
    public String name() {
        return "QAR";
    }

    @Override
    public int Tier() {
        return 3;
    }

}