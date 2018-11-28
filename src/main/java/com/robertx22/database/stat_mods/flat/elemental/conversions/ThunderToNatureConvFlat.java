package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.thunder_to.ThunderToNatureConversion;
import com.robertx22.stats.Stat;

public class ThunderToNatureConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToNatureConversion();

    }

    @Override
    public String GUID() {
	return "ThunderToNatureConvFlat";
    }

}
