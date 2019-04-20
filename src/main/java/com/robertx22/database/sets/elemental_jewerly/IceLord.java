package com.robertx22.database.sets.elemental_jewerly;

import java.util.HashMap;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stat_mods.percent.ManaRegenPercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.StatMod;

public class IceLord extends Set {

    @Override
    public String Name() {
	return "Ice Lord";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

	return new HashMap<Integer, StatMod>() {
	    {
		{
		    put(2, new ManaRegenPercent());
		    put(3, new SpellWaterDamagePercent());

		}
	    }
	};
    }

    @Override
    public List<GearItemSlot> GearTypes() {
	return this.jewerly();
    }

}
