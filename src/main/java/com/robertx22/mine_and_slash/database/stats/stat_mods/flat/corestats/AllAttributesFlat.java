package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.AllAttributes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

public class AllAttributesFlat extends StatMod {

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    public AllAttributesFlat() {

    }

    @Override
    public Stat GetBaseStat() {
        return new AllAttributes();
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 4;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "all_attributes_flat";
    }

}

