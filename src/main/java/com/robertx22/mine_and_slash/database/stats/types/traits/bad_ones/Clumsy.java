package com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;

import java.util.Arrays;
import java.util.List;

public class Clumsy extends Trait {

    public static String GUID = "clumsy";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new PhysicalDamageFlat().size(StatMod.Size.HALF_LESS));

    }

    @Override
    public String locNameForLangFile() {
        return "Clumsy";
    }
}
