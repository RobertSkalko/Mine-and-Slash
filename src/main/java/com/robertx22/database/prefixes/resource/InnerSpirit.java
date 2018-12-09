package com.robertx22.database.prefixes.resource;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class InnerSpirit extends Prefix {

    @Override
    public String GUID() {
	return "Inner Spirit";
    }

    @Override
    public List<StatMod> StatMods() {
	return Arrays.asList(new EnergyRegenFlat(), new EnergyRegenPercent());
    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }

}
