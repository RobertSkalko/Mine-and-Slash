package com.robertx22.stats.StatEffects.offense;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.ICrittable;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class CriticalDamageEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 1;
	}

	@Override
	public EffectSides Side() {
		return EffectSides.Source;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect, Unit source, Stat stat) {

		try {
			if (Effect instanceof ICrittable) {

				ICrittable icrit = (ICrittable) Effect;

				if (icrit.GetCrit()) {
					float multi = 1 + stat.Value / 100;
					Effect.Number *= multi;

					// System.out.println("dmg increased by crit");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Effect;
	}
}
