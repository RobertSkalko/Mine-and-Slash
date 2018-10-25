package com.robertx22.stats.StatEffects;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.utilityclasses.HealthUtils;

public class LifestealEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 15;
	}

	@Override
	public EffectSides Side() {
		return EffectSides.Source;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect, Unit source, Stat stat) {

		try {
			if (Effect instanceof DamageEffect && Effect.Type.equals(EffectTypes.BASIC_ATTACK)) {

				int healed = (int) (stat.Value * Effect.Number);

				System.out.println("Lifesteal stole " + healed);

				Effect.Source.heal(HealthUtils.DamageToMinecraftHealth(healed, Effect.Source));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Effect;
	}

}
