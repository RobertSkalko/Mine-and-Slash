package com.robertx22.mine_and_slash.database.unique_items.rings;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus.LessHealthMap;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingHermitsInsanity extends BaseUniqueRing {

    @Override
    public int Tier() {
        return 4;
    }

    @Override
    public String GUID() {
        return "ring_hermit_insanity";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LessHealthMap(), new ElementalPeneFlat(Elements.Elemental)
                .multi(3), new CriticalDamagePercent().multi(7));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Hermit's Ring of Insanity";
    }

    @Override
    public String locDescForLangFile() {
        return "Stop! Don't approach me..";
    }

}
