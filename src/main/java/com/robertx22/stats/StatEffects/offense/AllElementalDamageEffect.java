package com.robertx22.stats.StatEffects.offense;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class AllElementalDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
	return 15;
    }

    @Override
    public EffectSides Side() {
	return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

	try {
	    if (Effect instanceof DamageEffect) {

		DamageEffect dmgeffect = (DamageEffect) Effect;

		if (dmgeffect.Element.equals(stat.Element())) {

		    dmgeffect.Number *= 1 + data.Value / 100;

		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}