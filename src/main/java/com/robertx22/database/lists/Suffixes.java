package com.robertx22.database.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.suffixes.OfCriticalHits;
import com.robertx22.database.suffixes.OfVitality;
import com.robertx22.gearitem.Suffix;

public class Suffixes {
	public static HashMap<String, Suffix> All = new HashMap<String, Suffix>() {
		{
			{
				put(new OfVitality().Name(), new OfVitality());
				put(new OfCriticalHits().Name(), new OfCriticalHits());
			}
		}
	};

	public static List<Suffix> Weapon = new ArrayList<Suffix>() {
		{
			{
				add(new OfCriticalHits());

			}
		}
	};

	public static List<Suffix> Armor = new ArrayList<Suffix>() {
		{
			{
				add(new OfVitality());

			}
		}
	};
}
