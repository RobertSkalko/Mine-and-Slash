package com.robertx22.mine_and_slash.blocks.scrabble;

import com.robertx22.mine_and_slash.blocks.bases.NonFullBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ScrabbleBlock extends NonFullBlock {

    public ScrabbleBlock() {
        super(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(-1.0F, 3600000.0F)
            .noDrops());
    }

}
