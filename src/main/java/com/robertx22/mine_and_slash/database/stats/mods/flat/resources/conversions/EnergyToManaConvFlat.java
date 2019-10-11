package com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.resources.conversions.EnergyToManaConversion;

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
