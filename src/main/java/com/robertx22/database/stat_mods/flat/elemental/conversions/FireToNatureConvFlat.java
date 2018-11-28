package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.fire_to.FireToNatureConversion;
import com.robertx22.stats.Stat;

public class FireToNatureConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToNatureConversion();
    }

    @Override
    public String GUID() {
	return "FireToNatureConvFlat";
    }

}
