package com.robertx22.mine_and_slash.database.items.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletNature extends BaseUniqueBracelet {

    public BraceletNature() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "braceletnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalResistFlat(Elements.Nature).multi(2), new HealthFlat(), new HealthRegenPercent());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thorn Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Fools fight for treasure, but I keep on living.";
    }
}
