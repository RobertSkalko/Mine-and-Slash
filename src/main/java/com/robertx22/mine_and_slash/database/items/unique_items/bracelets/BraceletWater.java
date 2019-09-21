package com.robertx22.mine_and_slash.database.items.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletWater extends BaseUniqueBracelet {

    public BraceletWater() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.MEDIUM, LvlPointStat.VITALITY, StatReq.Size.SMALL);

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
        return "braceletwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalTransferFlat(Elements.Fire, Elements.Water).multi(2), new ElementalResistFlat(Elements.Water), new ElementalResistFlat(Elements.Fire));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Water));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Frostburn Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Burn them all! With Ice of course.";
    }
}
