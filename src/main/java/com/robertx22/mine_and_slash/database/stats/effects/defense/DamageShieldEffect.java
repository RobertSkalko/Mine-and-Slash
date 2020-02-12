package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class DamageShieldEffect implements IStatEffect {

    private DamageShieldEffect() {
    }

    public static DamageShieldEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {
                Effect.number -= data.val;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

    private static class SingletonHolder {
        private static final DamageShieldEffect INSTANCE = new DamageShieldEffect();
    }
}
