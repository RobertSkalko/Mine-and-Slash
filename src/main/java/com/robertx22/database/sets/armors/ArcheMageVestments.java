package com.robertx22.database.sets.armors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stat_mods.percent.ManaRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.StatMod;

public class ArcheMageVestments extends Set {

	@Override
	public String Name() {
		return "Archmage's Vestments";
	}

	@Override
	public HashMap<Integer, StatMod> AllMods() {

		return new HashMap<Integer, StatMod>() {
			{
				{
					put(4, new ManaRegenPercent());

				}
			}
		};
	}

	@Override
	public List<GearItemSlot> GearTypes() {
		return Arrays.asList(new Boots(), new Chest(), new Pants(), new Helmet());
	}

}