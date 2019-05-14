package com.robertx22.database.prefixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.PhysicalDamagePercent;
import com.robertx22.database.stats.StatMod;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;

public class Tough extends Prefix {

    public Tough() {
    }

    @Override
    public String GUID() {
	return "Tough";
    }

    @Override
    public List<StatMod> StatMods() {
	return Arrays.asList(new PhysicalDamagePercent());
    }

}
