package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.uncommon.CLOC;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class SocketData extends StatGroupData implements ITooltipList {

    public SocketData() {

    }

    public boolean isEmpty() {
	return this.Mods.size() == 0;
    }

    public SocketData(SocketData socket) {
	this.rarity = socket.rarity;
	this.Mods = socket.Mods;
    }

    @Store
    int rarity = 0;

    public ItemRarity GetRarity() {
	return Rarities.Items.get(rarity);
    }

    public String getPrefix() {
	return " ";
    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {

	List<String> list = new ArrayList<String>();

	if (isEmpty()) {
	    list.add(getPrefix() + CLOC.word("empty") + " " + CLOC.word("socket"));
	} else {

	    for (LevelAndStats part : this.GetAllStats(gear.level)) {
		for (StatModData data : part.mods) {
		    list.addAll(data.GetTooltipString(gear.GetRarity().StatPercents(), part.level, true));
		}
	    }

	}

	return list;

    }

}
