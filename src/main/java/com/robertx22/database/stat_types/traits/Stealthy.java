package com.robertx22.database.stat_types.traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.defense.DodgeMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class Stealthy extends Trait implements IAffectsOtherStats {

    public static String GUID = "Stealthy";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new DodgeMulti());

    }

    @Override
    public String Description() {
	return "";

    }

    @Override
    public String unlocString() {
	return "stealthy";
    }
}
