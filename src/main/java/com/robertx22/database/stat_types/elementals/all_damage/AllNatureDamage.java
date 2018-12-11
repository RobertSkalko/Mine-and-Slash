package com.robertx22.database.stat_types.elementals.all_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AllNatureDamage extends AllEleDamageBase {
    public static String GUID = "All Nature Damage";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Nature;
    }

    @Override
    public String unlocString() {
	return "all_nature_damage";
    }
}
