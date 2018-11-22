package com.libraries.prospector.traverse.world.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenPatch extends WorldGenerator {
    private final IBlockState state;
    private final int size;
    private List<Block> replaceableBlocks = new ArrayList<>();

    public WorldGenPatch(IBlockState state, int size, Block... replaceableBlocks) {
        this.state = state;
        this.size = size;
        for (Block block : replaceableBlocks)
            this.replaceableBlocks.add(block);
    }

    public WorldGenPatch(IBlockState state, int size) {
        this(state, size, Blocks.DIRT, Blocks.GRASS, Blocks.STONE);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(this.size - 2) + 2;
        int j = 1;

        for (int k = position.getX() - i; k <= position.getX() + i; ++k) {
            for (int l = position.getZ() - i; l <= position.getZ() + i; ++l) {
                int i1 = k - position.getX();
                int j1 = l - position.getZ();

                if (i1 * i1 + j1 * j1 <= i * i) {
                    for (int k1 = position.getY() - 1; k1 <= position.getY() + 1; ++k1) {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        Block block = worldIn.getBlockState(blockpos).getBlock();

                        if (replaceableBlocks.contains(block)) {
                            worldIn.setBlockState(blockpos, state, 2);
                            if (worldIn.getBlockState(blockpos.up()).getMaterial() == Material.VINE)
                                worldIn.setBlockToAir(blockpos.up());
                        }
                    }
                }
            }
        }
        return true;
    }
}