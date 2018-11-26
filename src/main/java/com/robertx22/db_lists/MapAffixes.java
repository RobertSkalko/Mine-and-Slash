package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.map_affixes.beneficial.BonusHealthAffix;

public class MapAffixes {
	public static HashMap<String, BaseMapAffix> All = new HashMap<String, BaseMapAffix>() {
		{
			{
				put(new BonusHealthAffix().GUID(), new BonusHealthAffix());

			}
		}
	};
}
