package com.robertx22.database.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class WaterResist extends BaseEleResist {
    public static String GUID = "Water Resist";

    public WaterResist() {
    }

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
	return "water_resist";
    }

}
