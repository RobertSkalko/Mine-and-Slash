package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.map_affixes.BonusHealth;

public class MapAffixes {
	public static HashMap<String, BaseMapAffix> All = new HashMap<String, BaseMapAffix>() {
		{
			{
				put(new BonusHealth().GUID(), new BonusHealth());

			}
		}
	};
}
