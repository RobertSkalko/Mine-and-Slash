package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.nature_to.NatureToFireConversion;
import com.robertx22.stats.Stat;

public class NatureToFireConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToFireConversion();
    }

    @Override
    public String GUID() {
	return "NatureToFireConvFlat";
    }

}
