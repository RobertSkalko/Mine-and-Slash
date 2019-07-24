package com.robertx22.mine_and_slash.saveclasses.effects;

import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.Arrays;
import java.util.List;

@Storable
public class StatusEffectData implements IStatsContainer {

    public StatusEffectData() {

    }

    public StatusEffectData(BaseStatusEffect effect) {
        this.GUID = effect.GUID();
    }

    @Store
    public String GUID;

    public BaseStatusEffect GetEffect() {
        return SlashRegistry.StatusEffects().get(GUID);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
        return Arrays.asList(new LevelAndStats(GetEffect().Stats(), level));
    }

}
