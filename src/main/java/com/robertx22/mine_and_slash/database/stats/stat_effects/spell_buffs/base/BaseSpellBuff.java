package com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs.base;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell.SpellType;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellBuffEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public abstract class BaseSpellBuff implements IStatEffect {

    // spell buffs need different priorities or else they will be random cus only 1 is allowed
    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    public abstract SpellType typeOfSpellAffected();

    public abstract SpellBuffType buffType();

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof SpellBuffEffect) {

                SpellBuffEffect spell = (SpellBuffEffect) Effect;

                if (spell.buffable.getBuffType().equals(this.typeOfSpellAffected())) {

                    spell.setBuff(this.buffType());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
