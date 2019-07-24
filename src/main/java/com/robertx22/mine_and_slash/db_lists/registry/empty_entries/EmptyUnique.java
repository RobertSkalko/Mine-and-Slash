package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.gearitemslots.Boots;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;

import java.util.ArrayList;
import java.util.List;

public class EmptyUnique implements IUnique {
    @Override
    public List<StatMod> uniqueStats() {
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
        return new Boots();
    }
}
