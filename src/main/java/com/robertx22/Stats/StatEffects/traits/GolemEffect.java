package com.robertx22.stats.StatEffects.traits;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class GolemEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 50;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect, Unit source, Stat stat) {

		try {
			if (Effect instanceof DamageEffect && Effect.GetTarget().equals(source)) {

				Effect.Number /= 1.1F;

				System.out.println("Golem trait reduced damage ");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Effect;
	}

}
