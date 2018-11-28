package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.thunder_to.ThunderToFireConversion;
import com.robertx22.stats.Stat;

public class ThunderToFireConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToFireConversion();

    }

    @Override
    public String GUID() {
	return "ThunderToFireConvFlat";
    }

}
