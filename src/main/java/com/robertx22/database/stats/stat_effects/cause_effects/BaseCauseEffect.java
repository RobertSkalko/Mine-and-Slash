package com.robertx22.database.stats.stat_effects.cause_effects;

import com.robertx22.database.stats.stat_effects.OnCauseDoEffect;
import com.robertx22.uncommon.effectdatas.EffectData;

public abstract class BaseCauseEffect {

    public abstract void activate(OnCauseDoEffect oncause, EffectData effect);

}
