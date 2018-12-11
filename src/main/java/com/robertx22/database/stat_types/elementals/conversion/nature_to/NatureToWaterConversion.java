package com.robertx22.database.stat_types.elementals.conversion.nature_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.ConversionMethod;

public class NatureToWaterConversion extends BaseConversionMod {

    @Override
    public String Guid() {
	return "Nature To Water Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new SpellNatureDamage(), new SpellWaterDamage()),
		new ConversionMethod(new AttackNatureDamage(), new AttackWaterDamage()));

    }

    @Override
    public String unlocString() {
	return "nature_water_conversion";
    }
}
