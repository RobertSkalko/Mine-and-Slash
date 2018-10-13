package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.mods.flat.ArmorFlat;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.flat.DamageFlat;
import com.robertx22.database.stats.mods.flat.FireDamageFlat;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.stats.StatMod;

public class StatMods {
	public static HashMap<String, StatMod> All = new HashMap<String, StatMod>() {
		{
			{
				put(new HealthFlat().GUID(), new HealthFlat());
				put(new ArmorFlat().GUID(), new ArmorFlat());
				put(new CriticalHitFlat().GUID(), new CriticalHitFlat());
				put(new CriticalDamageFlat().GUID(), new CriticalDamageFlat());
				put(new DamageFlat().GUID(), new DamageFlat());
				put(new FireDamageFlat().GUID(), new FireDamageFlat());

			}
		}
	};
}
