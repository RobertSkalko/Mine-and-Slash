package com.robertx22.database.stat_mods.traits.ele_lords;

import com.robertx22.database.stat_types.traits.ele_lords.LordOfEarthquakesTrait;
import com.robertx22.stats.Stat;

public class LordOfEarthquakesFlat extends BaseLordTrait {

    @Override
    public String GUID() {
	return "LordOfEarthQuakesFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LordOfEarthquakesTrait();
    }

}
