package com.robertx22.mine_and_slash.data_generation.statmods;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class SerializableStatMod extends StatMod {

    String stat;
    float min;
    float max;
    float min2;
    float max2;
    StatModTypes type;
    String guid;

    public SerializableStatMod(String stat, float min, float max, StatModTypes type, String guid, Size size, float min2, float max2) {
        this.stat = stat;
        this.min = min;
        this.max = max;
        this.type = type;
        this.guid = guid;
        this.size = size;
        this.min2 = min2;
        this.max2 = max2;
    }

    @Override
    public Stat GetBaseStat() {
        return SlashRegistry.Stats()
            .get(stat);
    }

    @Override
    public String GUID() {
        return guid;
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
    public float minSecond() {
        return min2;
    }

    @Override
    public float maxSecond() {
        return max2;
    }

    @Override
    public StatModTypes getModType() {
        return type;
    }

}
