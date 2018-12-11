package com.robertx22.database.stat_types.elementals.conversion.thunder_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.ConversionMethod;

public class ThunderToWaterConversion extends BaseConversionMod {

    @Override
    public String Guid() {
	return "Thunder To Water Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new SpellThunderDamage(), new SpellWaterDamage()),
		new ConversionMethod(new AttackThunderDamage(), new AttackWaterDamage()));

    }

    @Override
    public String unlocString() {
	return "thunder_water_conversion";
    }
}
