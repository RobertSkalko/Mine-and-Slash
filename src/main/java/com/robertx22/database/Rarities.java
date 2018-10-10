package com.robertx22.database;

import java.util.HashMap;

import com.robertx22.classes.Rarity;
import com.robertx22.database.rarities.general.*;

public class Rarities {

	public static HashMap<Integer, Rarity> General = new HashMap<Integer, Rarity>() {
		{
			put(0, new Common());
			put(1, new Uncommon());
			put(2, new Rare());
			put(3, new Epic());
			put(4, new Legendary());
			put(5, new Mythical());
			

		}
	};

}
