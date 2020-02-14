package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.EleWepDmg;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;

public class EleWepDmgEffect extends BaseDamageEffect {

    public static EleWepDmgEffect INSTANCE = new EleWepDmgEffect();

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        EleWepDmg wepStat = (EleWepDmg) stat;

        if (wepStat.weaponType().equals(effect.weaponType)) {
            if (effect.isElemental()) {
                effect.number *= data.getMultiplier();
            }
        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return stat instanceof EleWepDmg;
    }

}