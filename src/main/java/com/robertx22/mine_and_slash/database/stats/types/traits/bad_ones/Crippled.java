package com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.LessPhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Crippled extends Trait implements IAffectsOtherStats {

    public static String GUID = "crippled";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new LessHealthRegenMulti(), new LessPhysicalDamageMulti());

    }

    @Override
    public String locNameForLangFile() {
        return "Crippled";
    }
}
