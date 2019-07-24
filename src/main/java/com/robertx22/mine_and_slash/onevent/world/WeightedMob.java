package com.robertx22.mine_and_slash.onevent.world;

import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.entity.EntityType;

public class WeightedMob implements IWeighted {

    EntityType type;
    int weight;

    public WeightedMob(EntityType type, int weight) {
        this.type = type;
        this.weight = weight;

    }

    @Override
    public int Weight() {
        return weight;
    }
}
