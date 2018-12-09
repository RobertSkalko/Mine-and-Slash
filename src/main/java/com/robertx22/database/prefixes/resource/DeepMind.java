package com.robertx22.database.prefixes.resource;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.percent.ManaRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class DeepMind extends Prefix {

    @Override
    public String GUID() {
	return "Deep Mind";
    }

    @Override
    public List<StatMod> StatMods() {
	return Arrays.asList(new ManaRegenFlat(), new ManaRegenPercent());
    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }

}