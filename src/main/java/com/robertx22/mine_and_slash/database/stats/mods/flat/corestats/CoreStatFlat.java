package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.types.core_stats.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class CoreStatFlat extends BaseCoreStatFlat {

    public CoreStatFlat(BaseCoreStat stat) {
        super(stat);
    }

    @Override
    public BaseCoreStatFlat newGeneratedInstance(BaseCoreStat stat) {
        return new CoreStatFlat(stat);
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
