package com.robertx22.stats;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;

public interface IStatEffect {

	public enum EffectSides {
		Source, Target
	}

	public abstract EffectSides Side();

	public abstract int GetPriority();

	public abstract EffectData TryModifyEffect(EffectData Effect, Unit Source, StatData statData, Stat stat);

}
