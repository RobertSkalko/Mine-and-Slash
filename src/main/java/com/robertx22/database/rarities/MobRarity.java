package com.robertx22.database.rarities;

import com.robertx22.gearitem.Rarity;

public abstract class MobRarity extends Rarity {
	
	public abstract float StatMultiplier();
	
	public abstract float LootMultiplier();

}
