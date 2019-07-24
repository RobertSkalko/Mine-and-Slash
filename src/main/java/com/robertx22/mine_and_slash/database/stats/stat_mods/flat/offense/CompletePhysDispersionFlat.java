package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDispersion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class CompletePhysDispersionFlat extends StatMod {

    @Override
    public String GUID() {
        return "CompletePhysDispersionFlat";
    }

    @Override
    public float Min() {
        return 100;

    }

    @Override
    public float Max() {
        return 100;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new PhysicalDispersion();
    }

}

