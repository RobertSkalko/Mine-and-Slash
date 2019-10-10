package com.robertx22.database.stats.stat_effects.causes;

import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;

public class OnAttackDodgedCause extends BaseCause {

    @Override
    public boolean shouldActivate(EffectData Effect) {

        if (Effect instanceof DamageEffect) {

            DamageEffect dmgeffect = (DamageEffect) Effect;

            if (dmgeffect.isDodged) {
                return true;
            }

        }
        return false;
    }

}

