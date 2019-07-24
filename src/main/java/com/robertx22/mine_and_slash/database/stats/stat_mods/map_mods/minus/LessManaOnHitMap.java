package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.ManaOnHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessManaOnHitMap extends StatMod {

    public LessManaOnHitMap() {
    }

    @Override
    public String GUID() {
        return "LessManaOnHitMap";
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
        return new ManaOnHit();
    }

}