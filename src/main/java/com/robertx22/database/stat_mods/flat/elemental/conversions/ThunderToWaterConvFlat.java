package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.thunder_to.ThunderToWaterConversion;
import com.robertx22.stats.Stat;

public class ThunderToWaterConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToWaterConversion();

    }

    @Override
    public String GUID() {
	return "ThunderToWaterConvFlat";
    }

}
