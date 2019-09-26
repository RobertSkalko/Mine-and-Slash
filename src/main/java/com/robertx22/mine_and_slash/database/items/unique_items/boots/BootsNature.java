package com.robertx22.mine_and_slash.database.items.unique_items.boots;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BootsNature extends BaseUniqueBoots {

    public BootsNature() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "bootsnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthPercent().multi(3), new ElementalTransferFlat(Elements.Thunder, Elements.Nature), new ElementalResistFlat(Elements.Nature)
                .multi(4));

    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Tree Trunks";
    }

    @Override
    public String locDescForLangFile() {
        return "Nothing shall break my roots!";
    }
}
