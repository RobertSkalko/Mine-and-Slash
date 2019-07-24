package com.robertx22.mine_and_slash.database.stats.stat_types.traits.bad_and_good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.resources.LessManaMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Barbarian extends Trait implements IAffectsOtherStats {

    public static String GUID = "Barbarian";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new PhysicalDamageMulti(), new LessManaMulti());

    }

    @Override
    public String locNameForLangFile() {
        return "Barbarian";
    }
}
