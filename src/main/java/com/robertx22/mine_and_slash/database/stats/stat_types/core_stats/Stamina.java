package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class Stamina extends BaseCoreStat {

    @Override
    public int iconSpriteNumber() {
        return 17;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Energy, Armor and Armor Percent";
    }

    @Override
    public String GUID() {
        return "Stamina";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new EnergyFlat(), new ArmorFlat(), new ArmorPercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Stamina";
    }
}
