package com.robertx22.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.percent.MajorCriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.MajorCriticalHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueRing;

public class RingCrit extends BaseUniqueRing {

    public RingCrit() {

    }

    @Override
    public int Tier() {
	return 5;
    }

    @Override
    public String name() {
	return "Ring of Precision";
    }

    @Override
    public String GUID() {
	return "ringcrit0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new MajorCriticalHitPercent(), new MajorCriticalDamagePercent(), new CriticalHitFlat(),
		new EnergyRegenFlat());
    }

    @Override
    public String description() {
	return "Strike with Accuracy, strike once.";
    }

}
