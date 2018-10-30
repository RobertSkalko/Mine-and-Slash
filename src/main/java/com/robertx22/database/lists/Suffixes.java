package com.robertx22.database.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.suffixes.OfCriticalHits;
import com.robertx22.database.suffixes.OfManaRegen;
import com.robertx22.database.suffixes.OfRockSkin;
import com.robertx22.database.suffixes.OfTheSage;
import com.robertx22.database.suffixes.OfVitality;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;

public class Suffixes {

	public static HashMap<String, Suffix> All() {

		List<Suffix> all = new ArrayList<Suffix>();
		all.addAll(Weapon);
		all.addAll(Armor);
		all.addAll(Jewerly);

		HashMap<String, Suffix> map = new HashMap<String, Suffix>();

		for (Suffix s : all) {
			map.put(s.Name(), s);
		}

		return map;
	}

	public static List<Suffix> Weapon = new ArrayList<Suffix>() {
		{
			{
				add(new OfCriticalHits());
				add(new OfCriticalHits());
				add(new OfCriticalHits());

			}
		}
	};

	public static List<Suffix> Armor = new ArrayList<Suffix>() {
		{
			{
				add(new OfVitality());
				add(new OfRockSkin());

			}
		}
	};
	public static List<Suffix> Jewerly = new ArrayList<Suffix>() {
		{
			{
				add(new OfManaRegen());
				add(new OfTheSage());

			}
		}
	};

}
