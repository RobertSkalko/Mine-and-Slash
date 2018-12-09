package com.robertx22.unique_items.boots;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.weapon_damages.SwordDamageFlat;
import com.robertx22.database.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueBoots;

public class BootsWater extends BaseUniqueBoots {

    public BootsWater() {

    }

    @Override
    public int Tier() {
	return 18;
    }

    @Override
    public String GUID() {
	return "bootswater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new SwordDamageFlat(), new WaterSpellToAttackFlat(),
		new CriticalDamagePercent(), new WaterResistFlat(), new CrippleLifestealPercent());

    }

}
