package com.robertx22.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueRing;

public class UniqueRingOfDodge extends BaseUniqueRing {

	public UniqueRingOfDodge() {

	}

	@Override
	public int Tier() {
		return 0;
	}

	@Override
	public String name() {
		return "Ring of Swiftness";
	}

	@Override
	public String GUID() {
		return "RingOfSwiftness0";
	}

	@Override
	public List<StatMod> uniqueStats() {
		return Arrays.asList(new DodgeFlat(), new NatureResistFlat(), new HealthFlat());
	}

	@Override
	public String description() {
		return "Swift as the Wind.";
	}

	@Override
	public String slot() {
		return new Ring().Name();
	}
}
