package com.robertx22.database.stat_mods.flat.resources.conversions;

import com.robertx22.database.stat_types.resources.conversions.ManaToEnergyConversion;
import com.robertx22.stats.Stat;

public class ManaToEnergyConvFlat extends BaseResourceConversion {

    @Override
    public Stat GetBaseStat() {
	return new ManaToEnergyConversion();
    }

    @Override
    public String GUID() {
	return "ManaToEnergyConvFlat";
    }

}
