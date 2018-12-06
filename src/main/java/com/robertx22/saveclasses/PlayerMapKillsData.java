package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.db_lists.MapAffixes;
import com.robertx22.saveclasses.mapitem.MapAffixData;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class PlayerMapKillsData {

    public PlayerMapKillsData() {

    }

    private static HashMap<Integer, Integer> lootBonusPerTier = new HashMap<Integer, Integer>() {
	{
	    {
		put(0, 50);
		put(1, 30);
		put(5, 20);
		put(10, 15);
		put(15, 10);
		put(20, 5);
		put(30, 2);

	    }
	}
    };

    @Store
    private HashMap<String, KillsData> killsPerAffix = new HashMap<String, KillsData>();

    public void init() {

	for (BaseMapAffix affix : MapAffixes.All.values()) {

	    if (killsPerAffix.containsKey(affix.GUID()) == false) {
		killsPerAffix.put(affix.GUID(), new KillsData(affix.GUID()));
	    }
	}

    }

    public void onKill(MapItemData map) {

	for (MapAffixData affix : map.affixes) {

	    HashMap<String, KillsData> hash = new HashMap<String, KillsData>(killsPerAffix);
	    for (Entry<String, KillsData> entry : hash.entrySet()) {
		KillsData data = entry.getValue();
		if (data == null || data.isValid() == false) {
		    killsPerAffix.remove(entry.getKey());
		}
	    }

	    String guid = affix.GUID;

	    if (killsPerAffix.containsKey(guid)) {

		KillsData data = killsPerAffix.get(guid);
		if (data.guid == null || data.guid.isEmpty()) {
		    data.guid = affix.GUID;
		}

		data.count++;
		killsPerAffix.put(guid, data);
	    } else {
		killsPerAffix.put(affix.GUID, new KillsData(guid));
	    }
	}

    }

    public int getLootMulti(MapItemData map) {

	int bonus = 0;

	try {
	    HashMap<String, Integer> bonuses = getAffixesAndBonuses();

	    for (MapAffixData affix : map.affixes) {

		if (bonuses.containsKey(affix.GUID)) {
		    bonus += bonuses.get(affix.GUID);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return bonus;
    }

    private HashMap<String, Integer> getAffixesAndBonuses() {
	HashMap<String, Integer> tiers = new HashMap<String, Integer>();

	List<KillsData> values = new ArrayList<KillsData>(killsPerAffix.values());
	Collections.sort(values);

	for (int i = 0; i < values.size(); i++) {

	    for (Entry<Integer, Integer> lootentry : lootBonusPerTier.entrySet()) {

		if (i <= lootentry.getKey()) {
		    KillsData dat = values.get(i);

		    tiers.put(dat.guid, lootentry.getValue());

		    break;

		}
	    }

	}

	return tiers;

    }

}
