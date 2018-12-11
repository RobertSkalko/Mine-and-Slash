package com.robertx22.database.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class FireResist extends BaseEleResist {
    public static String GUID = "Fire Resist";

    public FireResist() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Fire;
    }

    @Override
    public String unlocString() {
	return "fire_resist";
    }

}
