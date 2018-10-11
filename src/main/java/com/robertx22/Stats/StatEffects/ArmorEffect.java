package com.robertx22.stats.StatEffects;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IEffects.IArmorReducable;

public class ArmorEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 10;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect) {

		if (Effect instanceof IArmorReducable) {

		}

		return Effect;
	}

}
