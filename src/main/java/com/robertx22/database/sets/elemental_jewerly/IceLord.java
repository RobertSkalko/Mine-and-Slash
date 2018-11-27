package com.robertx22.database.sets.elemental_jewerly;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stat_mods.percent.ManaRegenPercent;
import com.robertx22.database.stat_mods.percent.elemental.WaterDamagePercent;
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
					put(3, new WaterDamagePercent());

				}
			}
		};
	}

	@Override
	public List<GearItemSlot> GearTypes() {
		return Arrays.asList(new Ring(), new Necklace(), new Bracelet());
	}

}
