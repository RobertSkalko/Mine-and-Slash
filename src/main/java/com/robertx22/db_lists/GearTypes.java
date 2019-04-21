package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.gearitemslots.Axe;
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
import com.robertx22.database.gearitemslots.Shield;
import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;

public class GearTypes {
    public static HashMap<String, GearItemSlot> All = new HashMap<String, GearItemSlot>() {
	{
	    {
		put(new Boots().GUID(), new Boots());
		put(new Pants().GUID(), new Pants());
		put(new Helmet().GUID(), new Helmet());
		put(new Chest().GUID(), new Chest());
		put(new Ring().GUID(), new Ring());
		put(new Sword().GUID(), new Sword());
		put(new Necklace().GUID(), new Necklace());
		put(new Bracelet().GUID(), new Bracelet());
		put(new Bow().GUID(), new Bow());
		put(new Charm().GUID(), new Charm());
		put(new Hammer().GUID(), new Hammer());
		put(new Staff().GUID(), new Staff());
		put(new Axe().GUID(), new Axe());
		put(new Shield().GUID(), new Shield());

	    }

	}
    };
}
