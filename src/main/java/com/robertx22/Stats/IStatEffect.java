package com.robertx22.Stats;

import EffectDatas.EffectData;

public interface IStatEffect {

	public abstract int GetPriority();	
	public abstract EffectData TryModifyEffect(EffectData Effect);
	
}
