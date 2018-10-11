package com.robertx22.database;

import java.util.HashMap;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.database.stats.types.*;
import com.robertx22.enums.StatRefs;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class Stats {

	public static HashMap<StatRefs, Stat> Classes = new HashMap<StatRefs, Stat>() {
		{
			put(StatRefs.Health, new Health());
			put(StatRefs.Armor, new Armor());

		}
	};

	public static HashMap<StatRefs, StatMod> Mods = new HashMap<StatRefs, StatMod>() {
		{
			put(StatRefs.Health, new HealthFlat());

		}
	};

}
