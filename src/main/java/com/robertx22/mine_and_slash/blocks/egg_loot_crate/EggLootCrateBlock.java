package com.robertx22.mine_and_slash.blocks.egg_loot_crate;

import com.robertx22.mine_and_slash.blocks.bases.NonFullBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EggLootCrateBlock extends NonFullBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public EggLootCrateBlock() {
        super(Block.Properties.create(Material.DRAGON_EGG, MaterialColor.BLACK)
                .hardnessAndResistance(Float.MAX_VALUE)
                .lightValue(1));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return new EggLootCrateTileEntity();

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos,
                                    PlayerEntity player, Hand hand,
                                    BlockRayTraceResult ray) {
        if (world.isRemote) {
            return true;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof EggLootCrateTileEntity) {

            EggLootCrateTileEntity crate = (EggLootCrateTileEntity) tile;

            crate.tryActivate(player);

        }

        return true;
    }

}
