package com.robertx22.database.gearitemslots.bases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.lists.Prefixes;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.database.stats.mods.flat.ArmorFlat;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

public abstract class BaseArmor extends GearItemSlot {

	@Override
	public List<Suffix> PossibleSuffixes() {
		return new ArrayList<Suffix>(Suffixes.Armor);
	}

	@Override
	public List<Prefix> PossiblePrefixes() {
		return new ArrayList<Prefix>(Prefixes.Armor);
	}

	@Override
	public List<StatMod> PrimaryStats() {
		return Arrays.asList(new HealthFlat());
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new ArmorFlat());
	}

}
