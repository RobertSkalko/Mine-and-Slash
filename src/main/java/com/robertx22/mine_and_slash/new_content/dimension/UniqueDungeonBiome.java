package com.robertx22.mine_and_slash.new_content.dimension;

import com.robertx22.mine_and_slash.mmorpg.registers.common.WorldGenRegisters;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Predicate;

public class UniqueDungeonBiome extends BaseBlankBiome {

    public UniqueDungeonBiome() {
        super(new Biome.Builder());
        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, WorldGenRegisters.UNIQUE_DUNGEON_WORLD_FEATURE);

    }

    @Override
    public Predicate<ConfiguredFeature<?, ?>> getFilter() {
        return x -> x == WorldGenRegisters.UNIQUE_DUNGEON_WORLD_FEATURE;
    }
}
