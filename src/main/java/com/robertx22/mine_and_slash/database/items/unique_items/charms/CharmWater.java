package com.robertx22.mine_and_slash.database.items.unique_items.charms;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class CharmWater extends BaseUniqueCharm {

    public CharmWater() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.WISDOM, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

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
        return "charmwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaToEnergyConvFlat(), new ArmorFlat().size(StatMod.Size.HIGH), new ElementalPenePercent(Elements.Water));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalPeneFlat(Elements.Water).size(StatMod.Size.HIGH));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Charm of the River Dragon";
    }

    @Override
    public String locDescForLangFile() {
        return "My path cannot be stopped.";
    }
}
