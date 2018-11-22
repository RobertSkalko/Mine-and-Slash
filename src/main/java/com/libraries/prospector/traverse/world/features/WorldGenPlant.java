package com.libraries.prospector.traverse.world.features;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPlant extends WorldGenerator {

    private final IBlockState state;

    public WorldGenPlant(IBlockState state) {
        this.state = state;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            boolean canStay = true;

            if (state.getBlock() instanceof BlockBush && !(((BlockBush) state.getBlock()).canBlockStay(worldIn, blockpos, this.state))) {
                canStay = false;
            }

            if (worldIn.isAirBlock(blockpos) && canStay) {
                worldIn.setBlockState(blockpos, this.state, 2);
            }
        }

        return true;
    }
}
