package com.robertx22.database.prefixes.defense.element;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.stats.StatMod;

public class PrefixNatureRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
	return "Nature Shield";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new NatureResistFlat(), new HealthPercent(), new ArmorFlat());

    }

}
