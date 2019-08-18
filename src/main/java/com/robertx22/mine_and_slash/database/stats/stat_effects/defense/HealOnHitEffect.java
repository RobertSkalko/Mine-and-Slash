package com.robertx22.mine_and_slash.database.stats.stat_effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class HealOnHitEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.First.priority;
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
                new HealEffect(new HealData(Effect.target, Effect.targetData, (int) data.Value))
                        .Activate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

