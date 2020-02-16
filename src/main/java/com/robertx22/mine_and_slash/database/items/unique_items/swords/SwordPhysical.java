package com.robertx22.mine_and_slash.database.items.unique_items.swords;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueSword;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class SwordPhysical extends BaseUniqueSword {
    public SwordPhysical() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 9;
    }

    @Override
    public String GUID() {
        return "swordphysical0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CriticalHitPercent().size(StatMod.Size.VERY_HIGH), new CriticalDamagePercent().size(StatMod.Size.VERY_HIGH));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new PhysicalDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Blade of Doom";
    }

    @Override
    public String locDescForLangFile() {
        return "Toughest Opponents fear no pain.";
    }
}
