package com.robertx22.mine_and_slash.database.spells.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public abstract class BaseSpellBlock extends Block {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 15.0D, 14.0D);

    public BaseSpellBlock() {
        super(Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.RED)
                      .doesNotBlockMovement()
                      .hardnessAndResistance(5)
                      .sound(SoundType.WET_GRASS));

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext ctx) {
        return SHAPE;
    }

}
