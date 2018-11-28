package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.water_to.WaterToThunderConversion;
import com.robertx22.stats.Stat;

public class WaterToThunderConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToThunderConversion();

    }

    @Override
    public String GUID() {
	return "WaterToThunderConvFlat";
    }

}
