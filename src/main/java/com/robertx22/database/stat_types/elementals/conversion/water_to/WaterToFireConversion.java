package com.robertx22.database.stat_types.elementals.conversion.water_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.ConversionMethod;

public class WaterToFireConversion extends BaseConversionMod {

    @Override
    public String Guid() {
	return "Water To Fire Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new SpellWaterDamage(), new SpellFireDamage()),
		new ConversionMethod(new AttackWaterDamage(), new AttackFireDamage()));

    }

    @Override
    public String unlocString() {
	return "water_fire_conversion";
    }
}
