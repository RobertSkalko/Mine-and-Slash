package com.robertx22.unique_items.swords;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.PhysicalDamageFlat;
import com.robertx22.database.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.database.stat_mods.percent.MajorCriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.MajorCriticalHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueSword;

public class SwordPhysical extends BaseUniqueSword {
    public SwordPhysical() {

    }

    @Override
    public int Tier() {
	return 9;
    }

    @Override
    public String GUID() {
	return "swordphysical0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new PhysicalDamageFlat(), new MajorCriticalHitPercent(), new MajorCriticalDamagePercent(),
		new LessHealthRegenFlat());
    }

}
