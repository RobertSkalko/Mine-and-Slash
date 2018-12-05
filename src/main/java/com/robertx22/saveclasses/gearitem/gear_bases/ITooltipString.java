package com.robertx22.saveclasses.gearitem.gear_bases;

import java.util.List;

import com.robertx22.database.MinMax;

public interface ITooltipString {

    public abstract List<String> GetTooltipString(MinMax minmax, int level, boolean addPrefix);

}
