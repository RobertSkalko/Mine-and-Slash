package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Bow;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Necklace;
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
				put(new Necklace().Name(), new Necklace());
				put(new Bracelet().Name(), new Bracelet());
				put(new Bow().Name(), new Bow());
				put(new Charm().Name(), new Charm());
				put(new Hammer().Name(), new Hammer());

			}
		}
	};
}
