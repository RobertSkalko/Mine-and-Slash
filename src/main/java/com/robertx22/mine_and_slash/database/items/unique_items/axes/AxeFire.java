package com.robertx22.mine_and_slash.database.items.unique_items.axes;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueAxe;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class AxeFire extends BaseUniqueAxe {
    public AxeFire() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public String GUID() {
        return "axefire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CriticalHitPercent().multi(2), new CriticalDamagePercent(), new ElementalPeneFlat(Elements.Fire));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Axe of Blazing Cuts";
    }

    @Override
    public String locDescForLangFile() {
        return "Behold my dance of fire!";
    }
}
