package com.robertx22.mine_and_slash.database.stats.types.traits.high_crit;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.types.traits.bases.BaseTraitHighCritHit;

import java.util.Arrays;
import java.util.List;

public class HighCritAddLifesteal extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new LifestealFlat());

    }

    @Override
    public String GUID() {
        return "high_crit_add_lifesteal";
    }

    @Override
    public String locNameForLangFile() {
        return "Lifesteal on High Crit";
    }
}
