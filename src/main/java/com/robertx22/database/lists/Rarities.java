package com.robertx22.database.lists;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.items.Common;
import com.robertx22.database.rarities.items.Epic;
import com.robertx22.database.rarities.items.Legendary;
import com.robertx22.database.rarities.items.Mythical;
import com.robertx22.database.rarities.items.Rare;
import com.robertx22.database.rarities.items.Uncommon;
import com.robertx22.database.rarities.mobs.Boss;
import com.robertx22.database.rarities.mobs.Champion;
import com.robertx22.database.rarities.mobs.Elite;
import com.robertx22.database.rarities.mobs.Normal;
import com.robertx22.database.rarities.mobs.Veteran;
import com.robertx22.database.rarities.mobs.WorldBoss;

public class Rarities {

	public static final int MAXIMUM_ITEM_RARITY = 5;
	public static final int MAXIMUM_Mob_RARITY = 5;

	public static List<ItemRarity> Items = Arrays.asList(new Common(), new Uncommon(), new Rare(), new Epic(),
			new Legendary(), new Mythical());

	public static List<MobRarity> Mobs = Arrays.asList(new Normal(), new Veteran(), new Elite(), new Champion(),
			new Boss(), new WorldBoss());

}
