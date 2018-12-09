package com.robertx22.database.prefixes.resource;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.percent.HealthRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class BraveHeart extends Prefix {

    @Override
    public String GUID() {
	return "Brave Heart";
    }

    @Override
    public List<StatMod> StatMods() {
	return Arrays.asList(new HealthRegenFlat(), new HealthRegenPercent());
    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }

}