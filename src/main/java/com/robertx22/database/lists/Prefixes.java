package com.robertx22.database.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.prefixes.Energetic;
import com.robertx22.database.prefixes.Evasive;
import com.robertx22.database.prefixes.Flaming;
import com.robertx22.database.prefixes.Frosty;
import com.robertx22.database.prefixes.HardHitting;
import com.robertx22.database.prefixes.Hardened;
import com.robertx22.database.prefixes.HeavenlySkin;
import com.robertx22.database.prefixes.LifeStealing;
import com.robertx22.database.prefixes.Thorny;
import com.robertx22.database.prefixes.Thundering;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;

public class Prefixes {

	public static List<Prefix> Weapon = new ArrayList<Prefix>() {
		{
			{
				add(new Flaming());
				add(new Frosty());
				add(new Thorny());
				add(new Thundering());

				add(new HardHitting());
				add(new LifeStealing());

			}
		}
	};

	public static List<Prefix> Armor = new ArrayList<Prefix>() {
		{
			{
				add(new Hardened());
				add(new Evasive());
				add(new HeavenlySkin());

			}
		}
	};

	public static List<Prefix> Jewerly = new ArrayList<Prefix>() {
		{
			{
				add(new Energetic());

			}
		}
	};

	public static HashMap<String, Prefix> All() {

		List<Prefix> all = new ArrayList<Prefix>();
		all.addAll(Weapon);
		all.addAll(Armor);
		all.addAll(Jewerly);

		HashMap<String, Prefix> map = new HashMap<String, Prefix>();

		for (Prefix s : all) {
			map.put(s.Name(), s);
		}

		return map;
	}

}
