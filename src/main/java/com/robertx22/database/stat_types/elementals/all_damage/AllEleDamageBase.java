package com.robertx22.database.stat_types.elementals.all_damage;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.offense.AllElementalDamageEffect;

public abstract class AllEleDamageBase extends Stat implements IStatEffects {

    public AllEleDamageBase() {
	this.hasMinimumAmount = false;
    }

    @Override
    public boolean ScalesToLevel() {
	return false;
    }

    @Override
    public boolean IsPercent() {
	return true;
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new AllElementalDamageEffect());
    }

}
