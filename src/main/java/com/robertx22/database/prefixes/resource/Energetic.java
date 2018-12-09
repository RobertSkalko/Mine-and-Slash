package com.robertx22.database.prefixes.resource;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Energetic extends Prefix {

    public Energetic() {
    }

    @Override
    public String GUID() {
	return "Energetic";
    }

    @Override
    public List<StatMod> StatMods() {
	return Arrays.asList(new EnergyRegenPercent());
    }

}
