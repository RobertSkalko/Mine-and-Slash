package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.elementals.spell_to_attack.WaterSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class WaterSpellToAttackFlat extends BaseSpellToAttackFlat {

    public WaterSpellToAttackFlat() {

    }

    @Override
    public Stat GetBaseStat() {
	return new WaterSpellToAttackDMG();

    }

    @Override
    public String GUID() {
	return "BonusWaterDamageFlat";
    }

}
