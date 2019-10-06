package com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.game_changers.SteadyHand;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class SteadyHandEffect implements IStatEffect {

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
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                DamageEffect dmg = (DamageEffect) Effect;

                if (dmg.isDmgAllowed()) {
                    float multi = 1 + (float) SteadyHand.DMG_INCREASE_PERCENT / 100;
                    Effect.number *= multi;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
