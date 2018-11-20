package com.robertx22.database.rarities;

import com.robertx22.database.MinMax;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

public abstract class MapRarity extends Rarity {
	public abstract int AffixChance();

	public abstract MinMax AffixAmount();

	public abstract MinMax StatPercents();

	public abstract MinMax BonusLootAmount();

	public abstract MinMax BonusLootRarity();

}
