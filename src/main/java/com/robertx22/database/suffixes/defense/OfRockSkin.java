package com.robertx22.database.suffixes.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfRockSkin extends Suffix {

    public OfRockSkin() {
    }

    @Override
    public String GUID() {
	return "Of Rock Skin";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new ArmorPercent());

    }

}
