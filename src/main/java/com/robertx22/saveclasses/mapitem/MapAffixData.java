package com.robertx22.saveclasses.mapitem;

import com.robertx22.database.lists.MapAffixes;
import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.uncommon.enumclasses.AffectedEntities;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class MapAffixData {

	public MapAffixData() {

	}

	public MapAffixData(String guid, int percent, AffectedEntities affects) {
		this.GUID = guid;
		this.percent = percent;
	}

	@Store
	public String GUID;

	@Store
	public int percent;

	@Store
	public AffectedEntities affectedEntities;

	public BaseMapAffix getAffix() {

		return MapAffixes.All.get(GUID);
	}

}
