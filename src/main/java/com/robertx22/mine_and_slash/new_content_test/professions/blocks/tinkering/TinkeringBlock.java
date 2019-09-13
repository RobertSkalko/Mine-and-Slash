package com.robertx22.mine_and_slash.new_content_test.professions.blocks.tinkering;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class TinkeringBlock extends ProfessionBlock {

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TinkeringTile();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos,
                            Random rand) {

    }
}
