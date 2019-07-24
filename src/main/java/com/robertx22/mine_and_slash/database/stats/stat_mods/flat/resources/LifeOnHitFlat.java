package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.LifeOnHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LifeOnHitFlat extends StatMod {

    public LifeOnHitFlat() {
    }

    @Override
    public String GUID() {
        return "LifeOnHitFlat";
    }

    @Override
    public float Min() {
        return 0.5F;
    }

    @Override
    public float Max() {
        return 2F;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new LifeOnHit();
    }

}