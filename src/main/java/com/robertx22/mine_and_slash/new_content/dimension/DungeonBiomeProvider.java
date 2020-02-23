package com.robertx22.mine_and_slash.new_content.dimension;

import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;

public class DungeonBiomeProvider extends SingleBiomeProvider {
    public DungeonBiomeProvider() {
        // new in 1.15 - passing null here seems okay here because the constructor for this class is just { }
        super(new SingleBiomeProviderSettings(null).setBiome(BiomeRegister.DUNGEON_BIOME));
    }
}