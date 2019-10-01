package com.robertx22.mine_and_slash.database.items.unique_items.staffs;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueStaff;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffLifesteal extends BaseUniqueStaff {

    public StaffLifesteal() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String GUID() {
        return "uniquestafflifesteal0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LifestealFlat().multi(2), new LifeOnHitFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new PhysicalDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Vampire Staff";
    }

    @Override
    public String locDescForLangFile() {
        return "All blood will be mine, eventually.";
    }
}