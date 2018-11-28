package com.robertx22.database.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stat_types.elementals.conversion.water_to.WaterToFireConversion;
import com.robertx22.stats.Stat;

public class WaterToFireConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToFireConversion();
    }

    @Override
    public String GUID() {
	return "WaterToFireConvFlat";
    }

}
