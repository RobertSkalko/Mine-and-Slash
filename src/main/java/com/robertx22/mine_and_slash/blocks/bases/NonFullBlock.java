package com.robertx22.mine_and_slash.blocks.bases;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

public abstract class NonFullBlock extends Block {

    public static final DirectionProperty direction = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty light = RedstoneTorchBlock.LIT;

    public NonFullBlock(Properties properties) {
        super(properties.lightValue(15));

        this.setDefaultState(this.stateContainer.getBaseState()
                .with(direction, Direction.NORTH)
                .with(light, Boolean.valueOf(true)));

    }

    @Override
    public boolean isSolid(BlockState state) {
        return false;
    }

    @Override
    public int getLightValue(BlockState state) {
        return super.getLightValue(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState()
                .with(direction, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(direction, rot.rotate(state.get(direction)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(direction)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(direction, light);
    }

}
