package com.robertx22.saveclasses.effects;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.db_lists.StatusEffects;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class StatusEffectData implements Serializable, IStatsContainer {

    private static final long serialVersionUID = 1083875672946620050L;

    public StatusEffectData() {

    }

    public StatusEffectData(BaseStatusEffect effect) {
	this.GUID = effect.GUID();
    }

    @Store
    public String GUID;

    public BaseStatusEffect GetEffect() {
	return StatusEffects.All.get(GUID);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
	return Arrays.asList(new LevelAndStats(GetEffect().Stats(), level));
    }

}
