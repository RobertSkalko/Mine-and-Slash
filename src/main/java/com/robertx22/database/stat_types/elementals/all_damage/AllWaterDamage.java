package com.robertx22.database.stat_types.elementals.all_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AllWaterDamage extends AllEleDamageBase {
    public static String GUID = "All Water Damage";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Water;
    }

    @Override
    public String unlocString() {
	return "all_water_damage";
    }

}
