package com.robertx22.mine_and_slash.database.unique_items.staffs;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.LifestealPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffLifesteal implements IUnique {

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
    public GearItemSlot getGearSlot() {
        return Staff.INSTANCE;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LifestealFlat(), new LifestealPercent(), new LifeOnHitFlat());
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