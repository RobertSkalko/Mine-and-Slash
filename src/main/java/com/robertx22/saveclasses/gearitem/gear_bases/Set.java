package com.robertx22.saveclasses.gearitem.gear_bases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.database.IGUID;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class Set implements IWeighted, IGUID {

	public Set() {
	}

	public abstract String Name();

	@Override
	public String GUID() {
		return Name();
	}

	public int StatPercent = 100;

	@Override
	public int Weight() {
		return IWeighted.UncommonWeight;
	}

	public abstract List<GearItemSlot> GearTypes();

	public abstract HashMap<Integer, StatMod> AllMods();

	public boolean CanBePlacedOnItemSlot(String name) {

		for (GearItemSlot slot : GearTypes()) {
			if (slot.GUID().equals(name)) {
				return true;
			}
		}
		return false;

	}

	public List<StatMod> GetObtainedMods(Unit unit) {

		List<StatMod> mods = new ArrayList();

		for (Entry<Integer, StatMod> mod : this.AllMods().entrySet()) {

			if (unit.WornSets.get(this.GUID()) >= mod.getKey()) {
				mods.add(mod.getValue());
			}

		}

		return mods;
	}

}
