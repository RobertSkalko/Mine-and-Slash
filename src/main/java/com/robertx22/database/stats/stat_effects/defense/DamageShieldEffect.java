package com.robertx22.database.stats.stat_effects.defense;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;

public class DamageShieldEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return 20;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {
                Effect.Number -= data.Value;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
