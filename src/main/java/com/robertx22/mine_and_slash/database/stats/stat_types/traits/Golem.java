package com.robertx22.mine_and_slash.database.stats.stat_types.traits;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Golem extends Trait implements IAffectsOtherStats {

    public static String GUID = "Golem";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new HealthMulti());

    }

    @Override
    public String locNameForLangFile() {
        return "Golem";
    }
}
