package com.robertx22.db_lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.prefixes.defense.Evasive;
import com.robertx22.database.prefixes.defense.Hardened;
import com.robertx22.database.prefixes.defense.HeavenlySkin;
import com.robertx22.database.prefixes.defense.element.PrefixFireRes;
import com.robertx22.database.prefixes.defense.element.PrefixNatureRes;
import com.robertx22.database.prefixes.defense.element.PrefixThunderRes;
import com.robertx22.database.prefixes.defense.element.PrefixWaterRes;
import com.robertx22.database.prefixes.misc.ThirstOfAcid;
import com.robertx22.database.prefixes.misc.ThirstOfFlame;
import com.robertx22.database.prefixes.misc.ThirstOfFrost;
import com.robertx22.database.prefixes.misc.ThirstOfLightning;
import com.robertx22.database.prefixes.offense.FlameImbued;
import com.robertx22.database.prefixes.offense.Flaming;
import com.robertx22.database.prefixes.offense.FrostImbued;
import com.robertx22.database.prefixes.offense.Frosty;
import com.robertx22.database.prefixes.offense.HardHitting;
import com.robertx22.database.prefixes.offense.HeavenlyStrikes;
import com.robertx22.database.prefixes.offense.LightningImbued;
import com.robertx22.database.prefixes.offense.PoisonImbued;
import com.robertx22.database.prefixes.offense.Thorny;
import com.robertx22.database.prefixes.offense.Thundering;
import com.robertx22.database.prefixes.offense.Tough;
import com.robertx22.database.prefixes.resource.BraveHeart;
import com.robertx22.database.prefixes.resource.DeepMind;
import com.robertx22.database.prefixes.resource.Energetic;
import com.robertx22.database.prefixes.resource.InnerSpirit;
import com.robertx22.database.prefixes.resource.LifeStealing;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;

public class Prefixes {

    public static List<Prefix> Weapon = new ArrayList<Prefix>() {
	{
	    {

		add(new ThirstOfAcid());
		add(new ThirstOfFrost());
		add(new ThirstOfFlame());
		add(new ThirstOfLightning());

		add(new HardHitting());
		add(new LifeStealing());
		add(new HeavenlyStrikes());

	    }
	}
    };

    public static List<Prefix> Armor = new ArrayList<Prefix>() {
	{
	    {

		add(new Flaming());
		add(new Frosty());
		add(new Thorny());
		add(new Thundering());

		add(new Hardened());
		add(new Evasive());
		add(new HeavenlySkin());

		add(new PrefixFireRes());
		add(new PrefixWaterRes());
		add(new PrefixThunderRes());
		add(new PrefixNatureRes());

	    }
	}
    };

    public static List<Prefix> Jewerly = new ArrayList<Prefix>() {
	{
	    {

		add(new BraveHeart());
		add(new DeepMind());
		add(new InnerSpirit());

		add(new Energetic());
		add(new Tough());

		add(new PoisonImbued());
		add(new FlameImbued());
		add(new FrostImbued());
		add(new LightningImbued());

	    }
	}
    };

    private static HashMap<String, Prefix> all = null;

    public static HashMap<String, Prefix> All() {

	if (all == null) {

	    List<Prefix> list = new ArrayList<Prefix>();
	    list.addAll(Weapon);
	    list.addAll(Armor);
	    list.addAll(Jewerly);

	    HashMap<String, Prefix> map = new HashMap<String, Prefix>();

	    for (Prefix s : list) {
		map.put(s.GUID(), s);
	    }
	    all = map;

	}

	return all;
    }

}
