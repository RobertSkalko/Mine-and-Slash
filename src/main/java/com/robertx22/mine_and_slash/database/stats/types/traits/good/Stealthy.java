package com.robertx22.mine_and_slash.database.stats.types.traits.good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.DodgeRatingMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Stealthy extends Trait implements IAffectsOtherStats {

    public static String GUID = "stealthy";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new DodgeRatingMulti());

    }

    @Override
    public int Weight() {
        return 0;
    }

    @Override
    public String locNameForLangFile() {
        return "Stealthy";
    }
}
