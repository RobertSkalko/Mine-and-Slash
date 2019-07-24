package com.robertx22.mine_and_slash.database.stats.stat_effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class AllSpellDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof SpellDamageEffect) {

                SpellDamageEffect dmgeffect = (SpellDamageEffect) Effect;

                if (dmgeffect.getEffectType().equals(EffectData.EffectTypes.SPELL)) {
                    dmgeffect.number += data.Value * dmgeffect.spell.ScalingValue().Multi;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}