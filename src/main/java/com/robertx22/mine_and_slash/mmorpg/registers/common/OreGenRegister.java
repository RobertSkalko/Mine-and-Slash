package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class OreGenRegister {
    public static void register() {

        if (ModConfig.INSTANCE.Server.GENERATE_ORES.get()) {

            int amount = 3;

            for (int i = 0; i < ItemOre.Blocks.values().size(); i++) {

                if (amount < 1) {
                    amount = 1;
                }
                genOre(ItemOre.Blocks.get(i), amount--);
            }

        }

    }

    public static void genOre(Block block, int amount) {

        CountRangeConfig countConfig = new CountRangeConfig(amount, 0, 0, 60);
        OreFeatureConfig minableConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block
                .getDefaultState(), 8);

        ConfiguredFeature<?> feature = Biome.createDecoratedFeature(Feature.ORE, minableConfig, Placement.COUNT_RANGE, countConfig);

        for (Biome biome : Biome.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
        }

    }

}
