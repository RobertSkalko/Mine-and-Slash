package com.robertx22.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueRing;

public class RingFrostFire extends BaseUniqueRing {

	public RingFrostFire() {

	}

	@Override
	public int Tier() {
		return 0;
	}

	@Override
	public String name() {
		return "FrostFire Ring";
	}

	@Override
	public String GUID() {
		return "ringfrostfire0";
	}

	@Override
	public List<StatMod> uniqueStats() {
		return Arrays.asList(new SpellFireDamageFlat(), new SpellWaterDamageFlat());
	}

	@Override
	public String description() {
		return "I will attain perfect control.";
	}

}
