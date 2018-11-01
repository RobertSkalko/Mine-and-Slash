package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderResist extends BaseEleResist {
	public static String GUID = "Thunder Resist";

	public ThunderResist() {
	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Thunder;
	}

}
