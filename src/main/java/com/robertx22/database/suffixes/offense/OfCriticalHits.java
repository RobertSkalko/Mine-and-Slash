package com.robertx22.database.suffixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfCriticalHits extends Suffix {

    public OfCriticalHits() {
    }

    @Override
    public String GUID() {
	return "Of Critical Hits";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new CriticalHitFlat());

    }

}
