package com.robertx22.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleCriticalDamagePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueRing;

public class RingEnergy extends BaseUniqueRing {

    public RingEnergy() {

    }

    @Override
    public int Tier() {
	return 15;
    }

    @Override
    public String GUID() {
	return "ringenergy0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new EnergyRegenFlat(), new EnergyRegenPercent(), new ManaRegenFlat(), new HealthFlat(),
		new CrippleCriticalDamagePercent());
    }

}
