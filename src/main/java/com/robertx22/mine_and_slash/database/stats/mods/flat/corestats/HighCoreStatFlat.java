package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.types.core_stats.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighCoreStatFlat extends BaseCoreStatFlat {

    public HighCoreStatFlat(BaseCoreStat stat) {
        super(stat);
    }

    @Override
    public String GUID() {
        return "high_" + GetBaseStat().GUID() + "_" + this.Type().id;
    }

    @Override
    public BaseCoreStatFlat newGeneratedInstance(BaseCoreStat stat) {
        return new HighCoreStatFlat(stat);
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

}
