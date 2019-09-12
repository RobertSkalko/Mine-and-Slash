package com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class AlchemyBlock extends ProfessionBlock {

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AlchemyTile();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos,
                            Random rand) {

        double d0 = (double) ((float) pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
        double d1 = (double) ((float) pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
        double d2 = (double) ((float) pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);

        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);

        for (int i = 0; i < 5; i++) {
            d0 = (double) ((float) pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
            d1 = (double) ((float) pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
            d2 = (double) ((float) pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);

            worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}
