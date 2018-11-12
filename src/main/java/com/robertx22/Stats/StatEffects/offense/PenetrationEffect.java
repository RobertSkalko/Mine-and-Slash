package com.robertx22.stats.StatEffects.offense;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class PenetrationEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 0;
	}

	@Override
	public EffectSides Side() {
		return EffectSides.Source;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

		try {
			if (Effect instanceof IPenetrable) {
				IPenetrable ipene = (IPenetrable) Effect;
				ipene.SetArmorPenetration((int) data.Value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Effect;
	}

}
