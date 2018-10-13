package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.prefixes.Flaming;
import com.robertx22.gearitem.Prefix;

public class Prefixes {
	public static HashMap<String, Prefix> All = new HashMap<String, Prefix>() {
		{
			{
				put(new Flaming().Name(), new Flaming());

			}
		}
	};
}
