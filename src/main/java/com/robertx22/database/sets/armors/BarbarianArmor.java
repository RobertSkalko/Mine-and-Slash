package com.robertx22.database.sets.armors;

import java.util.HashMap;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.StatMod;

public class BarbarianArmor extends Set {

    @Override
    public String Name() {
	return "Barbarian's Armor";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

	return new HashMap<Integer, StatMod>() {
	    {
		{
		    put(2, new LifestealFlat());

		}
	    }
	};
    }

    @Override
    public List<GearItemSlot> GearTypes() {
	return this.armor();
    }

}
