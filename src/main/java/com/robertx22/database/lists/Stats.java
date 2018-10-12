package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.types.Health;
import com.robertx22.stats.Stat;

public class Stats {
	public static HashMap<String, Stat> All = new HashMap<String, Stat>() {
		{
			{
				put(new Health().Name(), new Health());
			}
		}
	};
}
