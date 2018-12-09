package com.robertx22.database.prefixes.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Hardened extends Prefix {

    public Hardened() {
    }

    @Override
    public String GUID() {
	return "Hardened";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new ArmorPercent());

    }

}
