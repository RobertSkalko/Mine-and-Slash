package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;

import java.util.Arrays;
import java.util.List;

public class Dexterity extends BaseCoreStat {

    public static final String GUID = "Dexterity";

    public static final Dexterity INSTANCE = new Dexterity();

    private Dexterity() {

    }

    @Override
    public String getIconPath() {
        return "core/dex";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Crit Hit, Dodge and Armor";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new CriticalHitFlat().multi(1.5F), new DodgeRatingFlat(), new ArmorFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Dexterity";
    }
}
