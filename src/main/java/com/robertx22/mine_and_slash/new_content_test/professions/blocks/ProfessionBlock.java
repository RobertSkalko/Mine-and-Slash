package com.robertx22.mine_and_slash.new_content_test.professions.blocks;

import com.robertx22.mine_and_slash.blocks.bases.BaseInventoryBlock;
import net.minecraft.block.material.Material;

public class ProfessionBlock extends BaseInventoryBlock {

    public ProfessionBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

}