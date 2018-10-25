package com.robertx22.gearitem;

import java.util.List;

import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.saveclasses.gearitem.StatModData;

public interface IStatsContainer {

	public List<StatModData> GetAllStats(GearItemData gear);
}
