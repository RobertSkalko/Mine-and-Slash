package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.prefixes.Flaming;
import com.robertx22.gearitem.BaseAffix;

public class Affixes {
	public static HashMap<String, BaseAffix> All = new HashMap<String, BaseAffix>() {
		{
			{
				put(new Flaming().Name(), new Flaming());
			}
		}
	};

}
