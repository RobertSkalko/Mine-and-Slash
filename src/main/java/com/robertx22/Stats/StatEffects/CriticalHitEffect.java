package com.robertx22.stats.StatEffects;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.ICrittable;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;
import com.robertx22.utilityclasses.RandomUtils;

public class CriticalHitEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 0;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect, Unit source, Stat stat) {

		if (Effect instanceof ICrittable && Effect.GetSource().equals(source)) {

			ICrittable icrit = (ICrittable) Effect;

			if (RandomUtils.roll(stat.Value)) {
				icrit.SetCrit(true);
				System.out.println("It's a crit");
			}

		}

		return Effect;
	}
}
