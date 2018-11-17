package com.robertx22.saveclasses.mapitem;

import com.robertx22.uncommon.enumclasses.AffectedEntities;

public class MapAffixData {

	public MapAffixData() {

	}

	public MapAffixData(String guid, int percent, AffectedEntities affects) {
		this.GUID = guid;
		this.percent = percent;
	}

	public String GUID;
	public int percent;
	public AffectedEntities affectedEntities;
}
