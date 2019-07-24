package com.robertx22.mine_and_slash.world_gen.biome_color_schemes.bases;

import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.block.Block;

public class BlockWeight implements IWeighted {

    public BlockWeight(Block block) {
        this.block = block;
    }

    public BlockWeight weight(int weight) {
        this.weight = weight;
        return this;
    }

    public Block block;

    public int weight = 1000;

    @Override
    public int Weight() {
        return weight;
    }

}
