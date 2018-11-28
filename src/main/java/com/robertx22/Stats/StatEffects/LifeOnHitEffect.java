package com.robertx22.stats.StatEffects;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class LifeOnHitEffect implements IStatEffect {

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
	    if (Effect instanceof DamageEffect && Effect.getEffectType().equals(EffectTypes.BASIC_ATTACK)) {

		int healed = (int) data.Value;

		DamageEffect dmgeffect = (DamageEffect) Effect;
		dmgeffect.healthHealed += healed;

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
