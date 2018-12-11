package com.robertx22.database.stat_types.traits.high_dodge;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_types.traits.bases.BaseTraitHighCritHit;
import com.robertx22.stats.StatMod;

public class HighDodgeAddCritDamage extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new CriticalDamageFlat());

    }

    @Override
    public String Guid() {
	return "HighDodgeAddCritDamage";
    }

    @Override
    public String unlocString() {
	return "crit_damage_on_high_dodge";
    }

}
