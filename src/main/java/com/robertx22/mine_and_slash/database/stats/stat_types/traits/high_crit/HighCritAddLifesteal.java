package com.robertx22.mine_and_slash.database.stats.stat_types.traits.high_crit;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bases.BaseTraitHighCritHit;

import java.util.Arrays;
import java.util.List;

public class HighCritAddLifesteal extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new LifestealFlat());

    }

    @Override
    public String GUID() {
        return "HighCritAddLifesteal";
    }

    @Override
    public String locNameForLangFile() {
        return "Lifesteal on High Crit";
    }
}
