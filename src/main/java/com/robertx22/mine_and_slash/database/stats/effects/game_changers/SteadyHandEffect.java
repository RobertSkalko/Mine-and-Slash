package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.game_changers.SteadyHand;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;

public class SteadyHandEffect extends BaseDamageEffect {

    public static final SteadyHandEffect INSTANCE = new SteadyHandEffect();

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
        float multi = 1 + (float) SteadyHand.DMG_INCREASE_PERCENT / 100;
        effect.number *= multi;

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.isDmgAllowed();
    }

}
