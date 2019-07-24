package com.robertx22.mine_and_slash.database.stats.stat_effects.causes;

import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;

public class OnBlockedCause extends BaseCause {

    @Override
    public boolean shouldActivate(EffectData Effect) {

        if (Effect instanceof DamageEffect) {

            DamageEffect dmgeffect = (DamageEffect) Effect;

            if (dmgeffect.isFullyBlocked || dmgeffect.isPartiallyBlocked) {
                return true;
            }

        }
        return false;
    }

}


