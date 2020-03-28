package com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Diseased extends Trait implements IAffectsOtherStats {

    public static String GUID = "diseased";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new HealthRegenPercent().size(StatMod.Size.HALF_LESS));

    }

    @Override
    public String locNameForLangFile() {
        return "Diseased";
    }
}
