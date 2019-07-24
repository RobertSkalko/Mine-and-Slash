package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;

import java.util.Arrays;
import java.util.List;

public class Wisdom extends BaseCoreStat {
    @Override
    public int iconSpriteNumber() {
        return 14;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Mana Regen, Energy Regen and Health Regen";
    }

    @Override
    public String GUID() {
        return "Wisdom";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new ManaRegenFlat(), new EnergyRegenFlat(), new HealthRegenFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Wisdom";
    }
}
