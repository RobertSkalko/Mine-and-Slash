package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.nature_to.NatureToWaterConversion;
import com.robertx22.stats.Stat;

public class NatureToWaterConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToWaterConversion();
    }

    @Override
    public String GUID() {
	return "NatureToWaterConvFlat";
    }

}
