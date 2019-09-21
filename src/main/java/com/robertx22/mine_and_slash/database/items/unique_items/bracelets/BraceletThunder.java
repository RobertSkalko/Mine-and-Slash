package com.robertx22.mine_and_slash.database.items.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletThunder extends BaseUniqueBracelet {

    public BraceletThunder() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 8;
    }

    @Override
    public String GUID() {
        return "braceletthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CriticalHitPercent().multi(2), new CriticalDamagePercent()
                .multi(2));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunder Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Dedication brings unparalleled might.";
    }
}
