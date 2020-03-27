package com.robertx22.mine_and_slash.database.stats.mods;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.misc.LuckStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class Mod extends StatMod {

    public static Mod LUCK_FLAT() {
        return new Mod(new LuckStat(), 5, 10);
    }

    Stat stat;
    float min;
    float max;
    StatModTypes type = StatModTypes.Flat;

    public Mod(Stat stat, float min, float max, StatModTypes type) {
        this.stat = stat;
        this.min = min;
        this.max = max;
        this.type = type;
    }

    public Mod(Stat stat, float min, float max) {
        this.stat = stat;
        this.min = min;
        this.max = max;
    }

    @Override
    public Stat GetBaseStat() {
        return stat;
    }

    @Override
    public float Min() {
        return min;
    }

    @Override
    public float Max() {
        return max;
    }

    @Override
    public StatModTypes getModType() {
        return type;
    }
}
