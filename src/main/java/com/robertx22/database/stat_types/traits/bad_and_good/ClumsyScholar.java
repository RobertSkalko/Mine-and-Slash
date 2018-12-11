package com.robertx22.database.stat_types.traits.bad_and_good;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.defense.LessArmorMulti;
import com.robertx22.database.stat_mods.multi.resources.ManaMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class ClumsyScholar extends Trait implements IAffectsOtherStats {

    public static String GUID = "Clumsy Scholar";

    @Override
    public String unlocString() {
	return "clumsy_scholar";
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new ManaMulti(), new LessArmorMulti());

    }

    @Override
    public String Description() {
	return "";
    }
}
