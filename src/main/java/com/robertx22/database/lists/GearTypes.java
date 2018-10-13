package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.gearitem.GearItemSlot;

public class GearTypes {
	public static HashMap<String, GearItemSlot> All = new HashMap<String, GearItemSlot>() {
		{
			{
				put(new Boots().Name(), new Boots());
			}
		}
	};
}
