package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.database.MinMax;

public interface ITooltipString {

	public abstract String GetTooltipString(MinMax minmax, int level, boolean addPrefix);

}
