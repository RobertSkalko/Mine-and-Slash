package com.robertx22.unique_items.bracelets;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.CriticalHitPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueBracelet;

public class BraceletThunder extends BaseUniqueBracelet {

    public BraceletThunder() {

    }

    @Override
    public int Tier() {
	return 8;
    }

    @Override
    public String GUID() {
	return "braceletthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellThunderDamageFlat(), new CriticalHitPercent(), new CriticalDamagePercent(),
		new CriticalHitFlat(), new CriticalDamageFlat(), new CrippleLifestealPercent());
    }

}
