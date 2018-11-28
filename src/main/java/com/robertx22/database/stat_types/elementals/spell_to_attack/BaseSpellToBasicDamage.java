package com.robertx22.database.stat_types.elementals.spell_to_attack;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.offense.SpellToBasicDamageEffect;

public abstract class BaseSpellToBasicDamage extends Stat implements IStatEffects {

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new SpellToBasicDamageEffect());
	}

	@Override
	public boolean ScalesToLevel() {
		return false;
	}

	@Override
	public boolean IsPercent() {
		return true;
	}

	public abstract Stat StatThatGiveDamage();

}
