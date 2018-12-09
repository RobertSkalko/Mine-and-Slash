package com.robertx22.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.weapon_damages.BowDamageFlat;
import com.robertx22.database.stat_mods.percent.DodgePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueRing;

public class RingDodge extends BaseUniqueRing {

    public RingDodge() {

    }

    @Override
    public int Tier() {
	return 1;
    }

    @Override
    public String GUID() {
	return "ringdodge0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new DodgeFlat(), new DodgePercent(), new NatureResistFlat(), new HealthFlat(),
		new BowDamageFlat());
    }

}
