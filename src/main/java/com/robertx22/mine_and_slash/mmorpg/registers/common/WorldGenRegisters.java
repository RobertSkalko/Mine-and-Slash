package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.DungeonFeature;
import com.robertx22.mine_and_slash.new_content.UniqueDungeonFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldGenRegisters {

    public static final ConfiguredFeature DUNGEON_WORLD_FEATURE =
        new DungeonFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
            .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG));

    public static final ConfiguredFeature UNIQUE_DUNGEON_WORLD_FEATURE =
        new UniqueDungeonFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
            .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG));

    private static void register() {

        System.out.println("Registering Mine and Slash Map World Gen");

        OreGenRegister.register();

        for (Biome biome : ForgeRegistries.BIOMES) {

        }

    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {

        register();

    }

    public static void add(Biome biome, ConfiguredFeature comp) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, comp);
    }

    public static <FC extends Feature<C>, C extends IFeatureConfig, P extends IPlacementConfig> ConfiguredFeature<C,
        FC> create(
        Feature feature, Placement<P> place, P placeConfig) {

        return feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
            .withPlacement(place.configure(placeConfig));

    }

}
