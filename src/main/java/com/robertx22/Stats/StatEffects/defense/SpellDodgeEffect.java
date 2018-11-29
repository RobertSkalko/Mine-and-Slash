package com.robertx22.stats.StatEffects.defense;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class SpellDodgeEffect implements IStatEffect {

    @Override
    public int GetPriority() {
	return 30;
    }

    @Override
    public EffectSides Side() {
	return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

	try {
	    if (Effect instanceof DamageEffect && Effect.getEffectType().equals(EffectTypes.SPELL)) {

		if (RandomUtils.roll(data.Value)) {
		    Effect.Number = 0;
		    Effect.canceled = true;
		}

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
