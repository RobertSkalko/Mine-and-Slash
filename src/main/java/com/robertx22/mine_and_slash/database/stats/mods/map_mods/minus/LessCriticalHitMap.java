package com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessCriticalHitMap extends StatMod {

    public LessCriticalHitMap() {
    }

    @Override
    public String GUID() {
        return "less_critical_hit_map";
    }

    @Override
    public float Min() {
        return -20;
    }

    @Override
    public float Max() {
        return -50;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return CriticalHit.getInstance();
    }

}