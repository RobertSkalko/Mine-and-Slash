package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;

import java.util.Arrays;
import java.util.List;

public class Dexterity extends BaseCoreStat {

    @Override
    public int iconSpriteNumber() {
        return 13;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Crit Hit, Dodge and Armor";
    }

    @Override
    public String GUID() {
        return "Dexterity";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new CriticalHitFlat(), new DodgeFlat(), new ArmorFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Dexterity";
    }
}
