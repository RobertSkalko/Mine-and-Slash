package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.stats.StatMod;

public class StatMods {
	public static HashMap<String, StatMod> All = new HashMap<String, StatMod>() {
		{
			{
				put(new HealthFlat().GUID(), new HealthFlat());
			}
		}
	};
}
