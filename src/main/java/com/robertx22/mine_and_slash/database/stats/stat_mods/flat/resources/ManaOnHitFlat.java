package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.ManaOnHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ManaOnHitFlat extends StatMod {

    public ManaOnHitFlat() {
    }

    @Override
    public String GUID() {
        return "ManaOnHitFlat";
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 3;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new ManaOnHit();
    }

}