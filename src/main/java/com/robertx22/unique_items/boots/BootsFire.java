package com.robertx22.unique_items.boots;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.weapon_damages.AxeDamageFlat;
import com.robertx22.database.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueBoots;

public class BootsFire extends BaseUniqueBoots {

    public BootsFire() {

    }

    @Override
    public int Tier() {
	return 18;
    }

    @Override
    public String GUID() {
	return "bootsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new AxeDamageFlat(), new FireSpellToAttackFlat(),
		new CriticalDamagePercent(), new CrippleLifeOnHitPercent());

    }

}
