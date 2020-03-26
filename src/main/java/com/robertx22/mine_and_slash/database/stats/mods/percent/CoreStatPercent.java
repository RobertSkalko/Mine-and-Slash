package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.BaseCoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class CoreStatPercent extends BaseCoreStatFlat {

    public CoreStatPercent(BaseCoreStat stat) {
        super(stat);
    }

    @Override
    public BaseCoreStatFlat newGeneratedInstance(BaseCoreStat stat) {
        return new CoreStatPercent(stat);
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

}

