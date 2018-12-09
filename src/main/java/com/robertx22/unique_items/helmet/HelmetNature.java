package com.robertx22.unique_items.helmet;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.percent.less.LessManaOnHitPercent;
import com.robertx22.database.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueHelmet;

public class HelmetNature extends BaseUniqueHelmet {

    public HelmetNature() {

    }

    @Override
    public int Tier() {
	return 19;
    }

    @Override
    public String GUID() {
	return "helmetnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new LowDodgeAddArmorFlat(), new SpellNatureDamageFlat(), new HealthRegenFlat(),
		new HealthFlat(), new NatureResistFlat(), new LessManaOnHitPercent());
    }

}