package com.robertx22.database.suffixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfCriticalDamage extends Suffix {

    public OfCriticalDamage() {
    }

    @Override
    public String GUID() {
	return "Of Critical Damage";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new CriticalDamageFlat());

    }

}
