package com.robertx22.mine_and_slash.database.stats.types.traits.bad_and_good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.LessArmorMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.ManaMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class ClumsyScholar extends Trait implements IAffectsOtherStats {

    public static String GUID = "clumsy_scholar";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ManaMulti(), new LessArmorMulti());

    }

    @Override
    public String locNameForLangFile() {
        return "Clumsy Scholar";
    }
}
