package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.uncommon.CLOC;

import info.loenwind.autosave.annotations.Storable;

@Storable
public class SocketsData extends StatGroupData implements ITooltipList {

    public SocketsData() {

    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {

	List<String> list = new ArrayList<String>();

	list.add(CLOC.word("socket") + ":");

	for (StatModData data : this.GetAllStats(gear.level)) {

	    list.addAll(data.GetTooltipString(gear.GetRarity().StatPercents(), gear.level, true));
	}

	return list;

    }

}
