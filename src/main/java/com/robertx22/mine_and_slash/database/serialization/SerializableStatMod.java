package com.robertx22.mine_and_slash.database.serialization;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class SerializableStatMod extends StatMod {

    String stat;
    float min;
    float max;
    StatTypes type;

    public SerializableStatMod(String stat, float min, float max, StatTypes type, float multi) {
        this.stat = stat;
        this.min = min;
        this.max = max;
        this.type = type;
        this.multiplier = multi;
    }

    @Override
    public Stat GetBaseStat() {
        return SlashRegistry.Stats().get(stat);
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
    public StatTypes Type() {
        return type;
    }

}
