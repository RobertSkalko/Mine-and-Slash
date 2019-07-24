package com.robertx22.mine_and_slash.database.stats.stat_effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.ICrittable;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class CriticalDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.afterThis(new CriticalHitEffect().GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof ICrittable) {

                ICrittable icrit = (ICrittable) Effect;

                if (icrit.GetCrit()) {
                    Effect.number *= data.getMultiplier();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }
}
