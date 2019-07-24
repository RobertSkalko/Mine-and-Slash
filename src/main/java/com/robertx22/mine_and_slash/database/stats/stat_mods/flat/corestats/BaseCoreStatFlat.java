package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

public abstract class BaseCoreStatFlat extends StatMod {

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 5;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

}
