package com.robertx22.database.stat_types.traits.bad_ones;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class Diseased extends Trait implements IAffectsOtherStats {

    public static String GUID = "Diseased";

    @Override
    public String unlocString() {
	return "diseased";
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {

	return Arrays.asList(new LessHealthRegenMulti());

    }

    @Override
    public String Description() {
	return "";
    }

}
