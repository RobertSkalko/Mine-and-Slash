package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;

import java.util.ArrayList;
import java.util.List;

public class EmptyUnique implements IUnique {

    private EmptyUnique() {
    }

    public static EmptyUnique getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatReq getRequirements() {
        return new StatReq(LvlPointStat.DEXTERITY, StatReq.Size.MEDIUM);
    }

    @Override
    public List<StatMod> uniqueStats() {
        return new ArrayList<>();
    }

    @Override
    public List<StatMod> primaryStats() {
        return new ArrayList<>();
    }

    @Override
    public String locDescLangFileGUID() {
        return "";
    }

    @Override
    public String locDescForLangFile() {
        return "";
    }

    @Override
    public String locNameLangFileGUID() {
        return "";
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return PlateBoots.INSTANCE;
    }

    private static class SingletonHolder {
        private static final EmptyUnique INSTANCE = new EmptyUnique();
    }
}
