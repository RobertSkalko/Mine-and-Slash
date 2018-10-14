package com.robertx22.effectdatas;

import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;

public class EffectUnitStat {

	public IStatEffects effects;
	public Unit source;
	public Stat stat;

	public EffectUnitStat(IStatEffects effects, Unit source, Stat stat) {
		this.effects = effects;
		this.source = source;
		this.stat = stat;
	}

}
