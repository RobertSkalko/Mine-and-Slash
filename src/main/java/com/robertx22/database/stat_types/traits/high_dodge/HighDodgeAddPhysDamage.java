package com.robertx22.database.stat_types.traits.high_dodge;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.PhysicalDamagePercent;
import com.robertx22.database.stat_types.traits.bases.BaseTraitHighCritHit;
import com.robertx22.stats.StatMod;

public class HighDodgeAddPhysDamage extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new PhysicalDamagePercent());

    }

    @Override
    public String Guid() {
	return "HighDodgeAddPhysDamage";
    }

    @Override
    public String Name() {
	return "Phys Damage On High Dodge";
    }

}
