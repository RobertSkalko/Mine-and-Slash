package com.robertx22.mine_and_slash.uncommon.effectdatas;

import java.util.Comparator;

import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class EffectUnitStat implements Comparator<EffectUnitStat> {

	public IStatEffect effect;
	public Unit source;
	public StatData stat;

	public EffectUnitStat(IStatEffect effect, Unit source, StatData stat) {
		this.effect = effect;
		this.source = source;
		this.stat = stat;
	}

	public EffectUnitStat() {

	}

	@Override
	public int compare(EffectUnitStat arg0, EffectUnitStat arg1) {

		if (arg0.effect.GetPriority() > arg1.effect.GetPriority()) {
			return 1;
		} else if (arg0.effect.GetPriority() < arg1.effect.GetPriority()) {
			return -1;
		} else {
			return 0;
		}

	}

}
