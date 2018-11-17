package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.mapitem.MapAffixData;

public class MapItemData {

	public int level;
	public int tier;
	public int rarity;

	public List<MapAffixData> playerAffixes = new ArrayList<MapAffixData>();
	public List<MapAffixData> mobAffixes = new ArrayList<MapAffixData>();
	public List<MapAffixData> playerAndMobAffixes = new ArrayList<MapAffixData>();

	public List<MapAffixData> getAllAffixes() {

		List<MapAffixData> all = new ArrayList<MapAffixData>();

		all.addAll(mobAffixes);
		all.addAll(playerAffixes);
		all.addAll(playerAndMobAffixes);

		return all;

	}

}
