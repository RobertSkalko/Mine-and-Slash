package com.robertx22.database.stat_types.elementals.conversion.fire_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.ConversionMethod;

public class FireToWaterConversion extends BaseConversionMod {

    @Override
    public String Guid() {
	return "Fire To Water Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new SpellFireDamage(), new SpellWaterDamage()),
		new ConversionMethod(new AttackFireDamage(), new AttackWaterDamage()));

    }

    @Override
    public String unlocString() {
	return "fire_water_conversion";
    }
}
