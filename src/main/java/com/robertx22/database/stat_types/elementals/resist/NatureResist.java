package com.robertx22.database.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class NatureResist extends BaseEleResist {
    public static String GUID = "Nature Resist";

    public NatureResist() {
    }

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
	return "nature_resist";
    }

}
