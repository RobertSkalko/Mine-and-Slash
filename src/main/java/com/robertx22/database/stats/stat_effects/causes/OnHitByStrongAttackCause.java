package com.robertx22.database.stats.stat_effects.causes;

import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;

public class OnHitByStrongAttackCause extends BaseCause {

    public OnHitByStrongAttackCause(int percenthp) {
        this.percenthp = percenthp;
    }

    private int percenthp = 5;

    @Override
    public boolean shouldActivate(EffectData Effect) {

        if (Effect instanceof DamageEffect) {

            DamageEffect dmgeffect = (DamageEffect) Effect;

            if (dmgeffect.Number > dmgeffect.targetUnit.healthData().Value / 100 * percenthp) {
                return true;
            }

        }
        return false;
    }

}
