package com.robertx22.database.stat_types.elementals.conversion.nature_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.stats.ConversionMethod;

public class NatureToThunderConversion extends BaseConversionMod {

    @Override
    public String Guid() {
	return "Nature To Thunder Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
	return Arrays.asList(new ConversionMethod(new SpellNatureDamage(), new SpellThunderDamage()),
		new ConversionMethod(new AttackNatureDamage(), new AttackThunderDamage()));

    }

    @Override
    public String unlocString() {
	return "nature_thunder_conversion";
    }
}
