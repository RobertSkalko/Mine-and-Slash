package com.robertx22.saveclasses.gearitem.gear_bases;

import java.util.List;

import com.robertx22.saveclasses.GearItemData;

public interface ITooltipList {

	public abstract List<String> GetTooltipString(GearItemData gear);
}
