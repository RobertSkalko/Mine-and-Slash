package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;

public class HalveNonCritDmgEffect extends BaseDamageEffect {

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
    public DamageEffect modifyEffect(DamageEffect effect, StatData data, Stat stat) {
        effect.number /= 2;
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return !effect.isCriticalHit() && effect.number > 0;
    }

}
