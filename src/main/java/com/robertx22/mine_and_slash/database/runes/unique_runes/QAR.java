package com.robertx22.mine_and_slash.database.runes.unique_runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseUniqueRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.List;

public class QAR extends BaseUniqueRune {

    @Override
    public List<StatMod> mods() {
        return StatMod.ofSize(new ElementalAffinityFlat(Elements.Physical).allSingleElementVariations(), StatMod.Size.VERY_LOW);
    }

    @Override
    public String name() {
        return "QAR";
    }

    @Override
    public int Weight() {
        return super.Weight() / 2;
    }

    @Override
    public int Tier() {
        return 3;
    }

}