package com.robertx22.unique_items.helmet;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.database.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueHelmet;

public class HelmetWater extends BaseUniqueHelmet {

    public HelmetWater() {

    }

    @Override
    public int Tier() {
	return 12;
    }

    @Override
    public String GUID() {
	return "helmetwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellWaterDamageFlat(), new ManaFlat(), new ManaRegenFlat(), new EnergyRegenPercent(),
		new WaterResistFlat(), new LessCriticalHitPercent());
    }

}