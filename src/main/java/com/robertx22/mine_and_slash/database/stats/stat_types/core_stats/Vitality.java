package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class Vitality extends BaseCoreStat {

    @Override
    public String locDescForLangFile() {
        return "Increases Health, Health percent and Health Regen";
    }

    @Override
    public String GUID() {
        return "Vitality";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new HealthFlat(), new HealthPercent(), new HealthRegenFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Vitality";
    }
}
