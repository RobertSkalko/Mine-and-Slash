package com.robertx22.stats.StatEffects.offense;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class BonusBasicDamageEffect implements IStatEffect {

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

				int dmg = (int) ((float) stat.Value * (float) Effect.Number / 100);
				DamageEffect bonus = new DamageEffect(Effect.Source, Effect.Target, dmg);
				bonus.Type = EffectTypes.BONUS_ATTACK;
				bonus.Element = stat.Element();
				bonus.Activate();
				// System.out.println("Added bonus " + stat.Element() + " dmg :" + dmg);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Effect;
	}

}
