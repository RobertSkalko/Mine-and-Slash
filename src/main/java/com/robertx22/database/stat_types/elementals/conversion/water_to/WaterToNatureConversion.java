package com.robertx22.database.stat_types.elementals.conversion.water_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.ConversionMethod;

public class WaterToNatureConversion extends BaseConversionMod {

    @Override
    public String Guid() {
	return "Water To Nature Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new SpellWaterDamage(), new SpellNatureDamage()),
		new ConversionMethod(new AttackWaterDamage(), new AttackNatureDamage()));

    }

    @Override
    public String unlocString() {
	return "water_nature_conversion";
    }
}
