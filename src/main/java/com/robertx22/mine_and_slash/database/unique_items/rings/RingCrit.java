package com.robertx22.mine_and_slash.database.unique_items.rings;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.MajorCriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.MajorCriticalHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingCrit extends BaseUniqueRing {

    public RingCrit() {

    }

    @Override
    public int Tier() {
        return 4;
    }

    @Override
    public String GUID() {
        return "ringcrit0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new MajorCriticalHitPercent().multi(2), new MajorCriticalDamagePercent()
                .multi(2), new EnergyRegenFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ring of Precision";
    }

    @Override
    public String locDescForLangFile() {
        return "Strike with Accuracy, strike once.";
    }
}
