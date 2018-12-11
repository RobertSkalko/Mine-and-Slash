package com.robertx22.database.stat_types.elementals.conversion.thunder_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.stats.ConversionMethod;

public class ThunderToFireConversion extends BaseConversionMod {

    @Override
    public String Guid() {
	return "Thunder To Fire Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new SpellThunderDamage(), new SpellFireDamage()),
		new ConversionMethod(new AttackThunderDamage(), new AttackFireDamage()));

    }

    @Override
    public String unlocString() {
	return "thunder_fire_conversion";
    }
}
