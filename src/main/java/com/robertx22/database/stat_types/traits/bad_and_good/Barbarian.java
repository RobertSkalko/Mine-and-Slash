package com.robertx22.database.stat_types.traits.bad_and_good;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.database.stat_mods.multi.resources.LessManaMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class Barbarian extends Trait implements IAffectsOtherStats {

    public static String GUID = "Barbarian";

    @Override
    public String unlocString() {
	return "barbarian";
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new PhysicalDamageMulti(), new LessManaMulti());

    }

    @Override
    public String Description() {
	return "";
    }
}
