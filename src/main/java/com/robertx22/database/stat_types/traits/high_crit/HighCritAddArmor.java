package com.robertx22.database.stat_types.traits.high_crit;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stat_types.traits.bases.BaseTraitHighCritHit;
import com.robertx22.stats.StatMod;

public class HighCritAddArmor extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new ArmorFlat(), new ArmorPercent());

    }

    @Override
    public String Guid() {
	return "HighCritAddArmor";
    }

    @Override
    public String unlocString() {
	return "armor_on_high_crit";
    }

}
