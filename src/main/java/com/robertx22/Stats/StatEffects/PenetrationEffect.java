package com.robertx22.stats.StatEffects;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class PenetrationEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 0;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect, Unit source, Stat stat) {

		if (Effect instanceof IPenetrable && Effect.GetSource().equals(source)) {
			IPenetrable ipene = (IPenetrable) Effect;
			ipene.SetArmorPenetration((int) stat.Value);
		}

		return Effect;
	}

}
