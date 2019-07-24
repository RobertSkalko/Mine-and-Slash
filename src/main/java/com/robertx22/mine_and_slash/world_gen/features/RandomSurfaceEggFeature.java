package com.robertx22.mine_and_slash.world_gen.features;

import com.mojang.datafixers.Dynamic;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class RandomSurfaceEggFeature extends Feature<NoFeatureConfig> {

    public RandomSurfaceEggFeature(
            Function<Dynamic<?>, ? extends NoFeatureConfig> dynamic) {
        super(dynamic);
    }

    @Override
    public boolean place(IWorld iworld,
                         ChunkGenerator<? extends GenerationSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        if (iworld.isAirBlock(pos)) {

            if (WorldUtils.isMapWorld(iworld)) {

                iworld.setBlockState(pos, BlockRegister.EGG_LOOT_CRATE_BLOCK.getDefaultState(), 2); // setblockstate needs to use IWORLD, NOT WORLD

                return true;
            }

        }
        return false;
    }
}
