package com.robertx22.mine_and_slash.world_gen.features;

import com.mojang.datafixers.Dynamic;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import com.robertx22.mine_and_slash.world_gen.processors.BiomeProcessor;
import com.robertx22.mine_and_slash.world_gen.types.FeatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Function;

public class RandomSurfaceDecoration extends Feature<NoFeatureConfig> {

    public RandomSurfaceDecoration(
            Function<Dynamic<?>, ? extends NoFeatureConfig> dynamic) {
        super(dynamic);
    }

    @Override
    public boolean place(IWorld iworld,
                         ChunkGenerator<? extends GenerationSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        IWP iwp = WorldUtils.getIWP(iworld);

        if (iwp != null) {

            FeatureType type = iwp.randomSmallSurfaceDecoration();
            ResourceLocation res = type.structureResourceLocation;
            pos = type.modifyPos(pos);

            if (res != null) {

                TemplateManager templatemanager = ((ServerWorld) iworld.getWorld()).getSaveHandler()
                        .getStructureTemplateManager();

                PlacementSettings placement = new PlacementSettings();
                placement.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
                placement.addProcessor(BlockIgnoreStructureProcessor.AIR);
                placement.addProcessor(new BiomeProcessor(iwp));

                Template template = templatemanager.getTemplate(res);

                if (type.canSpawn(iworld, pos, template)) {
                    template.addBlocksToWorld(iworld, pos, placement);

                } else {
                    return false;
                }

                return true;
            }

        }

        return false;

    }
}
