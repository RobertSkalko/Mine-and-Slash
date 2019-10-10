package com.robertx22.database.stats.stat_effects.causes;

import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;

public class OnAttackedAtLowHpCause extends BaseCause {

    public OnAttackedAtLowHpCause(int percenthp) {
        this.percenthp = percenthp;
    }

    private int percenthp = 10;

    @Override
    public boolean shouldActivate(EffectData Effect) {

        if (Effect instanceof DamageEffect) {

            DamageEffect dmgeffect = (DamageEffect) Effect;

            if (dmgeffect.targetUnit.health()
                    .CurrentValue(dmgeffect.Source, dmgeffect.sourceUnit) < dmgeffect.targetUnit
                    .healthData().Value / 100 * percenthp) {
                return true;
            }

        }
        return false;
    }

}
