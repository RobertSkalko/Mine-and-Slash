package com.robertx22.database.stat_types.elementals.attack_damage;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.offense.ElementalAttackDamageEffect;

public abstract class BaseElementalAttackDamage extends Stat implements IStatEffects {

	public BaseElementalAttackDamage() {
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ElementalAttackDamageEffect());
	}
}
