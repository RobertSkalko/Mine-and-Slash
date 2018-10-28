package com.robertx22.database.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.prefixes.Flaming;
import com.robertx22.database.prefixes.Hardened;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;

public class Prefixes {
	public static HashMap<String, Prefix> All = new HashMap<String, Prefix>() {
		{
			{
				put(new Flaming().Name(), new Flaming());
				put(new Hardened().Name(), new Hardened());

			}
		}
	};

	public static List<Prefix> Weapon = new ArrayList<Prefix>() {
		{
			{
				add(new Flaming());

			}
		}
	};

	public static List<Prefix> Armor = new ArrayList<Prefix>() {
		{
			{
				add(new Hardened());

			}
		}
	};

}
