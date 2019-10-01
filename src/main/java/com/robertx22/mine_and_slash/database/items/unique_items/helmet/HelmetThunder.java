package com.robertx22.mine_and_slash.database.items.unique_items.helmet;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetThunder extends BaseUniqueHelmet {

    public HelmetThunder() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "helmetthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellToAttackDMGFlat(Elements.Thunder), new EnergyRegenFlat()
                .multi(2));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAffinityFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunder Atronach Helmet";
    }

    @Override
    public String locDescForLangFile() {
        return "I see sparks all around me.";
    }
}