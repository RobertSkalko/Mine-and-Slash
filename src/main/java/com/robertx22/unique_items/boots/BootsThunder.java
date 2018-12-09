package com.robertx22.unique_items.boots;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.weapon_damages.HammerDamageFlat;
import com.robertx22.database.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueBoots;

public class BootsThunder extends BaseUniqueBoots {

    public BootsThunder() {

    }

    @Override
    public int Tier() {
	return 19;
    }

    @Override
    public String GUID() {
	return "bootsthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new HammerDamageFlat(), new ThunderSpellToAttackFlat(),
		new CriticalDamagePercent(), new ThunderResistFlat(), new CrippleLifeOnHitPercent());
    }

}
