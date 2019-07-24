package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Strength extends BaseCoreStat {

    @Override
    public int iconSpriteNumber() {
        return 16;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Physical DMG, Critical DMG, Physical DMG multi";
    }

    @Override
    public String GUID() {
        return "Strength";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new PhysicalDamagePercent(), new CriticalDamageFlat(), new PhysicalDamageMulti());
    }

    @Override
    public String locNameForLangFile() {
        return "Strength";
    }
}
