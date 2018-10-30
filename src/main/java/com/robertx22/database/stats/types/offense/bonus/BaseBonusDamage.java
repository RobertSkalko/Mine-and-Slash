package com.robertx22.database.stats.types.offense.bonus;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.offense.BonusBasicDamageEffect;

public abstract class BaseBonusDamage extends Stat implements IStatEffects {

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new BonusBasicDamageEffect());
	}

	@Override
	public boolean ScalesToLevel() {
		return false;
	}

	@Override
	public boolean IsPercent() {
		return true;
	}

}
