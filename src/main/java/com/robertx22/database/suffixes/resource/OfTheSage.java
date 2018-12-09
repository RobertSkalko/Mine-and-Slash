package com.robertx22.database.suffixes.resource;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.percent.ManaRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfTheSage extends Suffix {

    public OfTheSage() {
    }

    @Override
    public String GUID() {
	return "Of The Sage";
    }

    @Override
    public List<StatMod> StatMods() {
	return Arrays.asList(new ManaRegenPercent(), new ManaRegenFlat());
    }

    @Override
    public int Weight() {
	return this.EpicWeight;
    }

}
