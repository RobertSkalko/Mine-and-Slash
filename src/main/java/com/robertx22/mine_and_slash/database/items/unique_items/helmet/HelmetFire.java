package com.robertx22.mine_and_slash.database.items.unique_items.helmet;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetFire extends BaseUniqueHelmet {

    public HelmetFire() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.SMALL, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 17;
    }

    @Override
    public String GUID() {
        return "helmetfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new MajorArmorFlat(), new EnergyRegenFlat().multi(1.5F), new ElementalResistFlat(Elements.Fire));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flame Atronach Helmet";
    }

    @Override
    public String locDescForLangFile() {
        return "I see flames all around me.";
    }
}