package com.robertx22.database.stats.stat_effects;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;

public class ManaOnHitEffect implements IStatEffect {

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

		int mana_restored = (int) data.Value;

		DamageEffect dmgeffect = (DamageEffect) Effect;
		dmgeffect.manaRestored += mana_restored;

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
