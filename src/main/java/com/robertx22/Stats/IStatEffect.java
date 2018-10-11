package com.robertx22.stats;

import com.robertx22.effectdatas.EffectData;

public interface IStatEffect {

	public abstract int GetPriority();

	public abstract EffectData TryModifyEffect(EffectData Effect);

}
