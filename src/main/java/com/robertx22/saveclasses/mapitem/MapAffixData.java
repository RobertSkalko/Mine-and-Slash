package com.robertx22.saveclasses.mapitem;

import java.util.List;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.db_lists.MapAffixes;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.enumclasses.AffectedEntities;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class MapAffixData {

	public MapAffixData() {

	}

	public MapAffixData(BaseMapAffix affix, int percent) {
		this.GUID = affix.GUID();
		this.percent = percent;

		if (affix.isBeneficial()) {
			affectedEntities = AffectedEntities.Mobs;
		} else {
			affectedEntities = AffectedEntities.Players;
		}

	}

	public MapAffixData(String guid, int percent, AffectedEntities affects) {
		this.GUID = guid;
		this.percent = percent;
		this.affectedEntities = affects;
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

	public List<StatModData> GetAllStats() {
		return getAffix().Stats(percent);
	}

}
