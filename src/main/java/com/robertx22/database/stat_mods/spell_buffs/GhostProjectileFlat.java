package com.robertx22.database.stat_mods.spell_buffs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.spell_buff_traits.GhostProjectileTrait;
import com.robertx22.stats.Stat;

public class GhostProjectileFlat extends BaseTraitMod {

    public GhostProjectileFlat() {
    }

    @Override
    public String GUID() {
	return "GhostProjectileFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new GhostProjectileTrait();
    }

}
