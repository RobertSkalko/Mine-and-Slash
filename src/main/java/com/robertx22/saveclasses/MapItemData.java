package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.mapitem.MapAffixData;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class MapItemData {

	@Store
	public int level;

	@Store
	public int tier;

	@Store
	public int rarity;

	@Store
	public List<MapAffixData> playerAffixes = new ArrayList<MapAffixData>();

	@Store
	public List<MapAffixData> mobAffixes = new ArrayList<MapAffixData>();

	@Store
	public List<MapAffixData> playerAndMobAffixes = new ArrayList<MapAffixData>();

	public List<MapAffixData> getAllAffixes() {

		List<MapAffixData> all = new ArrayList<MapAffixData>();

		all.addAll(mobAffixes);
		all.addAll(playerAffixes);
		all.addAll(playerAndMobAffixes);

		return all;

	}

}
