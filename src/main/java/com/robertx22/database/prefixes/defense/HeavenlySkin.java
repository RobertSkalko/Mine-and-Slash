package com.robertx22.database.prefixes.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class HeavenlySkin extends Prefix {

    public HeavenlySkin() {
    }

    @Override
    public String GUID() {
	return "Heavenly Skin";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new ArmorPercent(), new HealthPercent());

    }

    @Override
    public int Weight() {
	return this.EpicWeight;
    }

}
