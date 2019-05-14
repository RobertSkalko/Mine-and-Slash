package com.robertx22.database.stats;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;

public interface IStatEffect {

	public enum EffectSides {
		Source, Target
	}

	public abstract EffectSides Side();

	public abstract int GetPriority();

	public abstract EffectData TryModifyEffect(EffectData Effect, Unit Source, StatData statData, Stat stat);

}
