package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.fire_to.FireToThunderConversion;
import com.robertx22.stats.Stat;

public class FireToThunderConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToThunderConversion();
    }

    @Override
    public String GUID() {
	return "FireToThunderConvFlat";
    }

}
