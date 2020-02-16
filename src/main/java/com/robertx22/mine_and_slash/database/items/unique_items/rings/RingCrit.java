package com.robertx22.mine_and_slash.database.items.unique_items.rings;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HighEnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.HighCriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.HighCriticalHitPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingCrit extends BaseUniqueRing {

    public RingCrit() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.TINY, LvlPointStat.INTELLIGENCE,
        StatReq.Size.SMALL
    );


    @Override
    public StatReq getRequirements() {
        return req;
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
        return Arrays.asList(new HighCriticalHitPercent(), new HighCriticalDamagePercent(), new HighEnergyRegenFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new SpellDamageFlat());
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
