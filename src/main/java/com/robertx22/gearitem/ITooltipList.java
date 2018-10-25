package com.robertx22.gearitem;

import java.util.List;

import com.robertx22.saveclasses.gearitem.GearItemData;

public interface ITooltipList {

	public abstract List<String> GetTooltipString(GearItemData gear);
}
