package com.robertx22.mine_and_slash.database.stats.stat_types.traits;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense.DodgeMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Stealthy extends Trait implements IAffectsOtherStats {

    public static String GUID = "Stealthy";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new DodgeMulti());

    }

    @Override
    public String locNameForLangFile() {
        return "Stealthy";
    }
}
