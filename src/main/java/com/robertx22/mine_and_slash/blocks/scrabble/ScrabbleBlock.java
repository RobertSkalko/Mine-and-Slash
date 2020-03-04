package com.robertx22.mine_and_slash.blocks.scrabble;

import com.robertx22.mine_and_slash.blocks.bases.NonFullBlock;
import com.robertx22.mine_and_slash.packets.proxies.OpenGuiWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ScrabbleBlock extends NonFullBlock {

    public ScrabbleBlock() {
        super(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(-1.0F, 3600000.0F)
            .noDrops());
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ScrabbleTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                             Hand hand, BlockRayTraceResult ray) {
        if (!world.isRemote) {
            return ActionResultType.CONSUME;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof ScrabbleTile) {
            OpenGuiWrapper.openScrabble(tile.getPos());

            return ActionResultType.SUCCESS;
        }

        return ActionResultType.CONSUME;
    }

}
