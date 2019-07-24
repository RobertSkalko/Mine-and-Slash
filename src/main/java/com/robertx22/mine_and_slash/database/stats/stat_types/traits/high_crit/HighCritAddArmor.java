package com.robertx22.mine_and_slash.database.stats.stat_types.traits.high_crit;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bases.BaseTraitHighCritHit;

import java.util.Arrays;
import java.util.List;

public class HighCritAddArmor extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ArmorFlat(), new ArmorPercent());

    }

    @Override
    public String GUID() {
        return "HighCritAddArmor";
    }

    @Override
    public String locNameForLangFile() {
        return "Armor on High Crit";
    }
}
