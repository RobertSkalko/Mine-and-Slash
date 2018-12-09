package com.robertx22.db_lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.suffixes.defense.OfElementResist;
import com.robertx22.database.suffixes.defense.OfImmortality;
import com.robertx22.database.suffixes.defense.OfRockSkin;
import com.robertx22.database.suffixes.defense.OfVitality;
import com.robertx22.database.suffixes.offense.OfCriticalDamage;
import com.robertx22.database.suffixes.offense.OfCriticalHits;
import com.robertx22.database.suffixes.offense.OfCriticalUnity;
import com.robertx22.database.suffixes.offense.pene.OfFirePene;
import com.robertx22.database.suffixes.offense.pene.OfNaturePene;
import com.robertx22.database.suffixes.offense.pene.OfThunderPene;
import com.robertx22.database.suffixes.offense.pene.OfWaterPene;
import com.robertx22.database.suffixes.resource.OfManaRegen;
import com.robertx22.database.suffixes.resource.OfTheSage;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;

public class Suffixes {

    public static List<Suffix> Weapon = new ArrayList<Suffix>() {
	{
	    {
		add(new OfCriticalHits());
		add(new OfCriticalDamage());
		add(new OfCriticalUnity());

	    }
	}
    };

    public static List<Suffix> Armor = new ArrayList<Suffix>() {
	{
	    {
		add(new OfVitality());
		add(new OfRockSkin());
		add(new OfElementResist());
		add(new OfImmortality());

	    }
	}
    };

    public static List<Suffix> Jewerly = new ArrayList<Suffix>() {
	{
	    {
		add(new OfVitality());
		add(new OfManaRegen());
		add(new OfTheSage());

		add(new OfFirePene());
		add(new OfNaturePene());
		add(new OfThunderPene());
		add(new OfWaterPene());
	    }
	}
    };

    private static HashMap<String, Suffix> all = null;

    public static HashMap<String, Suffix> All() {

	if (all == null) {

	    List<Suffix> list = new ArrayList<Suffix>();
	    list.addAll(Weapon);
	    list.addAll(Armor);
	    list.addAll(Jewerly);

	    HashMap<String, Suffix> map = new HashMap<String, Suffix>();

	    for (Suffix s : list) {
		map.put(s.GUID(), s);
	    }
	    all = map;
	}

	return all;
    }

}
