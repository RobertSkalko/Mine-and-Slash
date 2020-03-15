package com.robertx22.mine_and_slash.new_content.chests.trapped;

import com.robertx22.mine_and_slash.new_content.chests.MapChestBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;

public class TrappedMapChestBlock extends MapChestBlock {

    public TrappedMapChestBlock() {

    }

    // COPIED FROM TRAPPEDCHESTBLOCK
    @Override
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return MathHelper.clamp(ChestTileEntity.getPlayersUsing(blockAccess, pos), 0, 15);
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return side == Direction.UP ? blockState.getWeakPower(blockAccess, pos, side) : 0;
    }
    // COPIED FROM TRAPPEDCHESTBLOCK
}
