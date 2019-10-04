package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.MagicShieldPercent;

import java.util.Arrays;
import java.util.List;

public class Intelligence extends BaseCoreStat {

    public static String GUID = "Intelligence";

    @Override
    public int iconSpriteNumber() {
        return 15;
    }

    @Override
    public String locDescForLangFile() {
        return "Increase Magic Shield %, Mana Regen and Mana and All Elemental damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new ManaRegenFlat(), new ManaFlat(), new MagicShieldPercent()
                .multi(2), new AllEleDmgFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Intelligence";
    }
}




