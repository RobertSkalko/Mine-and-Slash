package com.robertx22.unique_items.hammers;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stat_mods.flat.PhysicalDamageFlat;
import com.robertx22.database.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.CriticalHitPercent;
import com.robertx22.database.stat_mods.percent.less.LessManaOnHitPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueHammer;

public class HammerPhysical extends BaseUniqueHammer {
    public HammerPhysical() {

    }

    @Override
    public int Tier() {
	return 8;
    }

    @Override
    public String GUID() {
	return "hammerphysical0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new PhysicalDamageFlat(), new ArmorPeneFlat(), new CriticalHitPercent(),
		new CriticalDamagePercent(), new CrippleLifeOnHitPercent(), new LessManaOnHitPercent());
    }

}