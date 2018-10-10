package com.robertx22.Stats.StatEffects;

import com.robertx22.Stats.IStatEffect;
import com.robertx22.Stats.IEffects.IArmorReducable;

import EffectDatas.EffectData;

public class ArmorEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 10;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect) {
		
		if (Effect instanceof IArmorReducable) {
			
			
			
		}
	
		
		
		return Effect;
	}

}
