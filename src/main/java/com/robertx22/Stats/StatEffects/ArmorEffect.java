package com.robertx22.stats.StatEffects;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.IArmorReducable;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.stats.IStatEffect;

public class ArmorEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 10;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect) {

		if (Effect instanceof IArmorReducable) {

			int pene = 0;

			if (Effect instanceof IPenetrable) {

			}

		}

		return Effect;
	}

}
