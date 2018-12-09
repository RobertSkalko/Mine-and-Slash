package com.robertx22.unique_items.hammers;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.CriticalHitPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueHammer;

public class HammerThunder extends BaseUniqueHammer {
    public HammerThunder() {

    }

    @Override
    public int Tier() {
	return 17;
    }

    @Override
    public String GUID() {
	return "hammerthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackThunderDamageFlat(), new ThunderSpellToAttackFlat(), new CriticalHitPercent(),
		new CriticalDamagePercent(), new CriticalHitFlat(), new CrippleLifeOnHitPercent(),
		new CrippleManaOnHitPercent());
    }

}
