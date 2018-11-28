package com.robertx22.stats.StatEffects.offense;

import com.robertx22.database.stat_types.elementals.spell_to_attack.BaseSpellToBasicDamage;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class SpellToBasicDamageEffect implements IStatEffect {

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
	    if (Effect instanceof DamageEffect && Effect.getEffectType().equals(EffectTypes.BASIC_ATTACK)
		    && stat instanceof BaseSpellToBasicDamage) {

		BaseSpellToBasicDamage basebonus = (BaseSpellToBasicDamage) stat;

		float percent = data.Value;
		float derivedvalue = (float) source.MyStats.get(basebonus.StatThatGiveDamage().GUID()).Value;

		int dmg = (int) (percent * derivedvalue / 100);

		DamageEffect dmgeffect = (DamageEffect) Effect;

		if (dmgeffect.BonusElementDamageMap.containsKey(stat.Element())) {
		    dmgeffect.BonusElementDamageMap.put(stat.Element(),
			    dmgeffect.BonusElementDamageMap.get(stat.Element()) + dmg);
		} else {
		    dmgeffect.BonusElementDamageMap.put(stat.Element(), dmg);
		}

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
