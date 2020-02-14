package com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.LifeOnHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessLifeOnHitMap extends StatMod {

    public LessLifeOnHitMap() {
    }

    @Override
    public String GUID() {
        return "less_life_on_hit_map";
    }

    @Override
    public float Min() {
        return -30;
    }

    @Override
    public float Max() {
        return -80;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return LifeOnHit.getInstance();
    }

}