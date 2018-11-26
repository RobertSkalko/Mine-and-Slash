package com.robertx22.db_lists;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.items.Common;
import com.robertx22.database.rarities.items.Epic;
import com.robertx22.database.rarities.items.Legendary;
import com.robertx22.database.rarities.items.Mythical;
import com.robertx22.database.rarities.items.Rare;
import com.robertx22.database.rarities.items.Uncommon;
import com.robertx22.database.rarities.maps.CommonMap;
import com.robertx22.database.rarities.maps.EpicMap;
import com.robertx22.database.rarities.maps.LegendaryMap;
import com.robertx22.database.rarities.maps.MythicalMap;
import com.robertx22.database.rarities.maps.RareMap;
import com.robertx22.database.rarities.maps.UncommonMap;
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

	public static List<MapRarity> Maps = Arrays.asList(new CommonMap(), new UncommonMap(), new RareMap(), new EpicMap(),
			new LegendaryMap(), new MythicalMap());

	public static List<MobRarity> Mobs = Arrays.asList(new Normal(), new Veteran(), new Elite(), new Champion(),
			new Boss(), new WorldBoss());

}
