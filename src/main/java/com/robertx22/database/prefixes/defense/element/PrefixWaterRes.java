package com.robertx22.database.prefixes.defense.element;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.stats.StatMod;

public class PrefixWaterRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
	return "Water Shield";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new WaterResistFlat(), new HealthPercent(), new ArmorFlat());

    }

}
