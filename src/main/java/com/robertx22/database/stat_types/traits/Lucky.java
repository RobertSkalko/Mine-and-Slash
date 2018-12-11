package com.robertx22.database.stat_types.traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.defense.CriticalHitMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class Lucky extends Trait implements IAffectsOtherStats {

    public static String GUID = "Lucky";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new CriticalHitMulti());

    }

    @Override
    public String Description() {
	return "";

    }

    @Override
    public String unlocString() {
	return "lucky";
    }

}
