package com.robertx22.database.stats.stat_effects;

import org.apache.logging.log4j.core.net.Priority;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.IStatEffect.EffectSides;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.cause_effects.BaseCauseEffect;
import com.robertx22.database.stats.stat_effects.causes.BaseCause;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class OnCauseDoEffect implements IStatEffect {

    public OnCauseDoEffect(BaseCause cause, int chance, EffectSides whoGetsEffect,
                           BaseCauseEffect causeEffect, EffectSides side) {
        this.cause = cause;
        this.chance = chance;
        this.whoGetsEffect = whoGetsEffect;
        this.causeEffect = causeEffect;
        this.side = side;

    }

    private BaseCauseEffect causeEffect;
    private BaseCause cause;
    private int chance;
    public EffectSides whoGetsEffect;
    public EffectSides side;

    @Override
    public int GetPriority() {
        return 30 + 1;
    } // TODO might be problematic

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (RandomUtils.roll(chance)) {
                if (cause.shouldActivate(Effect)) {
                    causeEffect.activate(this, Effect);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

