package com.robertx22.mine_and_slash.new_content.dimension;

import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;

public class UniqueDungeonBiomeProvider extends SingleBiomeProvider {
    public UniqueDungeonBiomeProvider() {
        // new in 1.15 - passing null here seems okay here because the constructor for this class is just { }
        super(new SingleBiomeProviderSettings(null).setBiome(BiomeRegister.UNIQUE_DUNGEON_BIOME));
    }
}