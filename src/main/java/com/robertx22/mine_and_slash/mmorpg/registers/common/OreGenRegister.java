package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
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

            for (int i = 0; i < IRarity.Legendary; i++) {

                Block block = ItemOre.Blocks.get(i);

                amount--;

                if (amount < 1) {
                    amount = 1;
                }

                genOre(block, amount, i);
            }

        }

    }

    public static void genOre(Block block, int amount, int rarity) {

        CountRangeConfig countConfig = new CountRangeConfig(amount, 0, 0, 40 / (rarity + 1));
        OreFeatureConfig minableConfig = new OreFeatureConfig(
            OreFeatureConfig.FillerBlockType.NATURAL_STONE, block.getDefaultState(), 8);

        ConfiguredFeature feature = Feature.ORE.withConfiguration(minableConfig)
            .withPlacement(Placement.COUNT_RANGE.configure(countConfig));

        for (Biome biome : Biome.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
        }

    }

}