package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.nature_to.NatureToThunderConversion;
import com.robertx22.stats.Stat;

public class NatureToThunderConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToThunderConversion();

    }

    @Override
    public String GUID() {
	return "NatureToThunderConvFlat";
    }

}
