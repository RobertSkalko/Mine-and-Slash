package com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense.DodgeMulti;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleHealthRegenPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class HangedMan extends BaseMajorArcana {

    public static final String GUID = "HangedMan";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CrippleHealthRegenPercent(), new DodgeMulti(), new HealthMulti(), new ElementalPeneFlat(Elements.Water));
    }

    @Override
    public String locNameForLangFile() {
        return "The Hanged Man";
    }
}
