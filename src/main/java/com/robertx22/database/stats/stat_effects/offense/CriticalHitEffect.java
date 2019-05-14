package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.interfaces.ICrittable;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class CriticalHitEffect implements IStatEffect {

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
	    if (Effect instanceof ICrittable) {

		ICrittable icrit = (ICrittable) Effect;

		if (RandomUtils.roll(data.Value)) {
		    icrit.SetCrit(true);
		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }
}
