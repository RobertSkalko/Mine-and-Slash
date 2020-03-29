package com.robertx22.mine_and_slash.database.runes.unique_runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseUniqueRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;

import java.util.Arrays;
import java.util.List;

public class PSI extends BaseUniqueRune {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new CriticalDamageFlat().size(StatMod.Size.HALF_MORE));
    }

    @Override
    public String name() {
        return "PSI";
    }

    @Override
    public int getTier() {
        return 2;
    }

}
