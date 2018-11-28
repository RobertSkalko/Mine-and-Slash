package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.water_to.WaterToNatureConversion;
import com.robertx22.stats.Stat;

public class WaterToNatureConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToNatureConversion();

    }

    @Override
    public String GUID() {
	return "WaterToNatureConvFlat";
    }

}
