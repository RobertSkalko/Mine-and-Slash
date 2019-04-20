package com.robertx22.stats.StatEffects.spell_buffs.base;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.SpellBuffEffect;
import com.robertx22.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public abstract class BaseSpellBuff implements IStatEffect {

    @Override
    public int GetPriority() {
	return 0;
    }

    @Override
    public EffectSides Side() {
	return EffectSides.Source;
    }

    public abstract SpellType typeOfSpellAffected();

    public abstract SpellBuffType buffType();

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

	try {
	    if (Effect instanceof SpellBuffEffect) {

		SpellBuffEffect spell = (SpellBuffEffect) Effect;

		if (spell.buffable.getType().equals(this.typeOfSpellAffected())) {

		    spell.setBuff(this.buffType());
		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
