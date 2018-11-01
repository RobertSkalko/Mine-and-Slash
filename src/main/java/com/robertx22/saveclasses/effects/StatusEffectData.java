package com.robertx22.saveclasses.effects;

import java.io.Serializable;
import java.util.List;

import com.robertx22.database.lists.StatusEffects;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;

public class StatusEffectData implements Serializable, IStatsContainer {

	private static final long serialVersionUID = 1083875672946620050L;

	public StatusEffectData() {

	}

	public StatusEffectData(BaseStatusEffect effect) {
		this.GUID = effect.GUID();
	}

	public String GUID;

	public BaseStatusEffect GetEffect() {
		return StatusEffects.All.get(GUID);
	}

	@Override
	public List<StatModData> GetAllStats(int level) {
		return GetEffect().Stats();
	}

}
