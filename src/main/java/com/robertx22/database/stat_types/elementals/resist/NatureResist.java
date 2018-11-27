package com.robertx22.database.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class NatureResist extends BaseEleResist {
	public static String GUID = "Nature Resist";

	public NatureResist() {
	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

}
