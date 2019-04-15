package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.uncommon.CLOC;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class SocketsListData implements IStatsContainer, ITooltipList {

    @Store
    public List<SocketData> sockets = new ArrayList();

    @Override
    public List<String> GetTooltipString(GearItemData gear) {
	List<String> list = new ArrayList<String>();

	if (sockets.size() > 0) {

	    list.add(CLOC.word("socket") + ":");

	    for (SocketData socket : sockets) {
		list.addAll(socket.GetTooltipString(gear));
	    }
	}

	return list;
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

	List<LevelAndStats> mods = new ArrayList();

	for (SocketData socket : sockets) {
	    mods.addAll(socket.GetAllStats(level));
	}

	return mods;

    }

}
