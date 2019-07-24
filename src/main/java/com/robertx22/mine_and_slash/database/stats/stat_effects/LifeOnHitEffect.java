package com.robertx22.mine_and_slash.database.stats.stat_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class LifeOnHitEffect implements IStatEffect {

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
            if (Effect instanceof DamageEffect && Effect.getEffectType()
                    .equals(EffectTypes.BASIC_ATTACK)) {

                if (Effect.canceled == false) {
                    int healed = (int) data.Value;

                    DamageEffect dmgeffect = (DamageEffect) Effect;
                    dmgeffect.healthHealed += healed;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
