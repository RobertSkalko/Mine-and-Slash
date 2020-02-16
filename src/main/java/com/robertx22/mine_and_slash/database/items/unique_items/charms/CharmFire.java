package com.robertx22.mine_and_slash.database.items.unique_items.charms;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.HighElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class CharmFire extends BaseUniqueCharm {

    public CharmFire() {

    }

    static StatReq req = new StatReq(
            LvlPointStat.STRENGTH, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 12;
    }

    @Override
    public String GUID() {
        return "charmfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
                new ManaRegenPercent(), new HighElementalPeneFlat(Elements.Fire),
                new ElementalResistFlat(Elements.Fire)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Heavenly Fire Charm";
    }

    @Override
    public String locDescForLangFile() {
        return "Bath in flames, thrive!";
    }
}
