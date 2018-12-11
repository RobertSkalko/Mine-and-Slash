package com.robertx22.database.stat_types.resources.conversions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.resources.EnergyRegen;
import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.stats.ConversionMethod;

public class EnergyToManaConversion extends BaseConversionMod {

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new EnergyRegen(), new ManaRegen()));

    }

    @Override
    public String Guid() {
	return "EnergyToManaConversion";
    }

    @Override
    public String unlocString() {
	return "energy_to_mana_conversion";
    }

}
