package com.robertx22.mine_and_slash.blocks.simple;

import com.robertx22.mine_and_slash.blocks.bases.NonFullBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext;

import java.util.Arrays;
import java.util.List;

public class AttunementBlock extends NonFullBlock {

    public AttunementBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3F));
    }

    @Deprecated
    public List<ItemStack> getDrops(BlockState p_220076_1_,
                                    LootContext.Builder p_220076_2_) {
        return Arrays.asList(new ItemStack(this));
    }

}
