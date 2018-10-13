package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.types.Armor;
import com.robertx22.database.stats.types.CriticalDamage;
import com.robertx22.database.stats.types.CriticalHit;
import com.robertx22.database.stats.types.Damage;
import com.robertx22.database.stats.types.Health;
import com.robertx22.stats.Stat;

public class Stats {
	public static HashMap<String, Stat> All = new HashMap<String, Stat>() {
		{
			{
				put(new Health().Name(), new Health());
				put(new Armor().Name(), new Armor());
				put(new CriticalDamage().Name(), new CriticalDamage());
				put(new CriticalHit().Name(), new CriticalHit());
				put(new Damage().Name(), new Damage());

			}
		}
	};
}
