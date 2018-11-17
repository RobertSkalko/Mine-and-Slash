package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.map_affixes.BaseMapEffect;
import com.robertx22.database.map_affixes.BonusHealth;

public class MapMods {
	public static HashMap<String, BaseMapEffect> All = new HashMap<String, BaseMapEffect>() {
		{
			{
				put(new BonusHealth().GUID(), new BonusHealth());

			}
		}
	};
}
