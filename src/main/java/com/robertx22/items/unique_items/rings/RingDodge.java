package com.robertx22.items.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.weapon_damages.BowDamageFlat;
import com.robertx22.database.stat_mods.percent.DodgePercent;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.unique_items.bases.BaseUniqueRing;

import baubles.api.BaubleType;

public class RingDodge extends BaseUniqueRing {

    public RingDodge(BaubleType type) {
		super(type);
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
