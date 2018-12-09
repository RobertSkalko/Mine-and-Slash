package com.robertx22.database.prefixes.defense.element;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.stats.StatMod;

public class PrefixFireRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
	return "Fire Shield";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new FireResistFlat(), new HealthPercent(), new ArmorFlat());

    }

}
