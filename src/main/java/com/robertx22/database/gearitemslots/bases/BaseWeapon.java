package com.robertx22.database.gearitemslots.bases;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.lists.Prefixes;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.flat.DamageFlat;
import com.robertx22.database.stats.mods.flat.LifeOnHitFlat;
import com.robertx22.database.stats.mods.flat.LifestealFlat;
import com.robertx22.gearitem.Prefix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

public abstract class BaseWeapon extends GearItemSlot {
	@Override
	public List<Suffix> PossibleSuffixes() {
		return Suffixes.Weapon;
	}

	@Override
	public List<Prefix> PossiblePrefixes() {
		return Prefixes.Weapon;
	}

	@Override
	public List<StatMod> PrimaryStats() {
		return Arrays.asList(new DamageFlat());
	}

	@Override
	public List<StatMod> PossibleSecondaryStats() {
		return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat(), new LifestealFlat(), new LifeOnHitFlat());
	}

}
