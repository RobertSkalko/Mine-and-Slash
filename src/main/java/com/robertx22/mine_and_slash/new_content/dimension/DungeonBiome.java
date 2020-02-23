package com.robertx22.mine_and_slash.new_content.dimension;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DungeonBiome extends Biome {
    public DungeonBiome(Builder biomeBuilder) {
        super(biomeBuilder.surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(SurfaceBuilder.DEFAULT, SurfaceBuilder.AIR_CONFIG))
            .precipitation(Biome.RainType.RAIN)
            .category(Biome.Category.NONE)
            .depth(0.0F)
            .scale(0.025F)
            .temperature(0.8F)
            .downfall(0.4F)
            .waterColor(0x676767)
            .waterFogColor(329011)
            .parent((String) null));

        this.getSpawns(EntityClassification.AMBIENT)
            .clear();
        this.getSpawns(EntityClassification.CREATURE)
            .clear();
        this.getSpawns(EntityClassification.MONSTER)
            .clear();
        this.getSpawns(EntityClassification.WATER_CREATURE)
            .clear();
    }

    @Override
    public float getSpawningChance() {
        return 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSkyColorByTemp(float currentTemperature) {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}
