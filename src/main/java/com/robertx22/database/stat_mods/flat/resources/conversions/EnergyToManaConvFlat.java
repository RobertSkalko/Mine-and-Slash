package com.robertx22.database.stat_mods.flat.resources.conversions;

import com.robertx22.database.stat_types.resources.conversions.EnergyToManaConversion;
import com.robertx22.stats.Stat;

public class EnergyToManaConvFlat extends BaseResourceConversion {

    @Override
    public Stat GetBaseStat() {
	return new EnergyToManaConversion();
    }

    @Override
    public String GUID() {
	return "EnergyToManaConvFlat";
    }

}
