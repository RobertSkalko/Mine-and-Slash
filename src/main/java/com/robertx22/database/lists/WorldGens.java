package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.world_gens.BaseWorldGen;

public class WorldGens {
	public static HashMap<String, BaseWorldGen> All = new HashMap<String, BaseWorldGen>() {
		{
			{
				// put(new SpellFrostBolt().GUID(), new SpellFrostBolt());

			}
		}
	};

}
