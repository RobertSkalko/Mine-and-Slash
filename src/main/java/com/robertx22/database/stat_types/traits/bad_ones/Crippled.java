package com.robertx22.database.stat_types.traits.bad_ones;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.offence.LessPhysicalDamageMulti;
import com.robertx22.database.stat_mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class Crippled extends Trait implements IAffectsOtherStats {

    public static String GUID = "Crippled";

    @Override
    public String unlocString() {
	return "crippled";
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {

	return Arrays.asList(new LessHealthRegenMulti(), new LessPhysicalDamageMulti());

    }

    @Override
    public String Description() {
	return "";
    }

}
