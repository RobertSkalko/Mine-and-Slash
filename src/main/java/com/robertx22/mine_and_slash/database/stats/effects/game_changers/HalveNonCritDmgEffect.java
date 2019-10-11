package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class HalveNonCritDmgEffect implements IStatEffect {

    public static final HalveNonCritDmgEffect INSTANCE = new HalveNonCritDmgEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                DamageEffect dmg = (DamageEffect) Effect;

                if (!dmg.isCriticalHit()) {
                    if (Effect.number > 0) {
                        Effect.number /= 2;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
