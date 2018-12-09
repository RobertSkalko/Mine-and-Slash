package com.robertx22.unique_items.bracelets;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.percent.less.LessCriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueBracelet;

public class BraceletFire extends BaseUniqueBracelet {

    public BraceletFire() {

    }

    @Override
    public int Tier() {
	return 3;
    }

    @Override
    public String GUID() {
	return "braceletfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellFireDamageFlat(), new FireSpellToAttackFlat(), new FireResistFlat(),
		new LessCriticalDamagePercent(), new CrippleLifeOnHitPercent());
    }

}
