package com.robertx22.database.prefixes.resource;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.LifestealPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class LifeStealing extends Prefix {

    public LifeStealing() {
    }

    @Override
    public String GUID() {
	return "Life Stealing";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new LifestealPercent());
    }

}
