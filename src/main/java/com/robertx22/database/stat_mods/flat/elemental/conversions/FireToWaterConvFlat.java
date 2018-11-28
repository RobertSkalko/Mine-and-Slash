package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.fire_to.FireToWaterConversion;
import com.robertx22.stats.Stat;

public class FireToWaterConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToWaterConversion();
    }

    @Override
    public String GUID() {
	return "FireToWaterConvFlat";
    }

}
