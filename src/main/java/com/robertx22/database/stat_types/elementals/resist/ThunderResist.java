package com.robertx22.database.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderResist extends BaseEleResist {
    public static String GUID = "Thunder Resist";

    public ThunderResist() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Thunder;
    }

    @Override
    public String unlocString() {
	return "thunder_resist";
    }

}
