package com.robertx22.database.prefixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.percent.MajorCriticalHitPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class HeavenlyStrikes extends Prefix {

    @Override
    public String GUID() {
	return "Heavenly Strikes";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new CriticalHitFlat(), new MajorCriticalHitPercent());

    }

    @Override
    public int Weight() {
	return this.EpicWeight;
    }

}
