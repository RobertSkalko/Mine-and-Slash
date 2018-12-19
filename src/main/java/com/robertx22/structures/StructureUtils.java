package com.robertx22.structures;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class StructureUtils {

    public static BlockPos getTopLiquidBlock(World world, BlockPos pos) {
	Chunk chunk = world.getChunkFromBlockCoords(pos);
	BlockPos blockpos;
	BlockPos blockpos1;

	for (blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos
		.getY() >= 0; blockpos = blockpos1) {
	    blockpos1 = blockpos.down();
	    IBlockState state = chunk.getBlockState(blockpos1);

	    if (state.getBlock() instanceof BlockLiquid)
		break;
	}

	return blockpos;
    }

}
