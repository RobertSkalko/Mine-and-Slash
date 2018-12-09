package com.robertx22.unique_items.helmet;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.database.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueHelmet;

public class HelmetFire extends BaseUniqueHelmet {

    public HelmetFire() {

    }

    @Override
    public int Tier() {
	return 17;
    }

    @Override
    public String GUID() {
	return "helmetfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new LowDodgeAddArmorFlat(), new SpellFireDamageFlat(), new EnergyRegenFlat(),
		new MajorArmorFlat(), new FireResistFlat(), new LessCriticalHitPercent());
    }

}