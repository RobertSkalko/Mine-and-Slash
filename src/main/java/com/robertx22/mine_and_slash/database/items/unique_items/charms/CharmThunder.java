package com.robertx22.mine_and_slash.database.items.unique_items.charms;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class CharmThunder extends BaseUniqueCharm {

    public CharmThunder() {

    }

    static StatReq req = new StatReq(
            LvlPointStat.WISDOM, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 19;
    }

    @Override
    public String GUID() {
        return "charmthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
                new ElementalSpellDamagePercent(Elements.Thunder), new ManaRegenPercent(),
                new ElementalPenePercent(Elements.Thunder)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAffinityFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Charm of Heavenly Tribulations";
    }

    @Override
    public String locDescForLangFile() {
        return "Jade is only worth after it is polished.";
    }
}
