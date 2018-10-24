package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;

public class GearTypes {
	public static HashMap<String, GearItemSlot> All = new HashMap<String, GearItemSlot>() {
		{
			{
				put(new Boots().Name(), new Boots());
				put(new Pants().Name(), new Pants());
				put(new Helmet().Name(), new Helmet());
				put(new Chest().Name(), new Chest());
				put(new Ring().Name(), new Ring());
				put(new Sword().Name(), new Sword());
			}
		}
	};
}
