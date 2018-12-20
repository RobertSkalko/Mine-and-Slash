package com.robertx22.structures;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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

    public static List<EntityPlayer> getAllPlayersNear(BlockPos pos, World world, int num) {

	List<EntityPlayer> players = new ArrayList<EntityPlayer>();

	for (EntityPlayer player : world.playerEntities) {

	    if (player.getDistance(pos.getX(), pos.getY(), pos.getZ()) < num) {
		players.add(player);
	    }
	}
	return players;
    }

    public static BlockPos findEmptySpaceInAirForStructure(BlockPos near, World world, BlockPos size) {

	boolean can = false;

	int i = 0;
	while (can == false || i > 500) {
	    i++;

	    BlockPos first = null;

	    int num = world.rand.nextInt(50);
	    int num2 = world.rand.nextInt(50);

	    if (world.rand.nextBoolean()) {
		num = -num;
	    }
	    if (world.rand.nextBoolean()) {
		num2 = -num2;
	    }
	    BlockPos change = new BlockPos(num, 0, num2);

	    if (world.rand.nextBoolean()) {
		first = world.getTopSolidOrLiquidBlock(near.add(change));

	    } else {
		first = world.getTopSolidOrLiquidBlock(near.subtract(change));
	    }

	    first = first.add(0, size.getY(), 0); // push it above by how tall the building is

	    if (isAllSpaceAir(first, world, size)) {
		can = true;
		return first;
	    }

	}

	return null;
    }

    public static boolean isAllSpaceAir(BlockPos pos, World world, BlockPos size) {

	for (int x = 0; x < size.getX(); x++) {
	    for (int y = 0; y < size.getY(); y++) {
		for (int z = 0; z < size.getZ(); z++) {

		    BlockPos add = new BlockPos(x, y, z);
		    BlockPos end = pos.add(add);

		    if (world.isAirBlock(end) == false) {
			return false;
		    }

		}

	    }

	}

	return true;
    }
}
