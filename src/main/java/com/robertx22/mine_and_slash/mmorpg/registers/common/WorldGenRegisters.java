package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.world_gen.features.RandomSurfaceDecoration;
import com.robertx22.mine_and_slash.world_gen.features.RandomSurfaceEggFeature;
import com.robertx22.mine_and_slash.world_gen.features.RandomSurfaceTreasure;
import com.robertx22.mine_and_slash.world_gen.structures.FloatingIslandStructure;
import com.robertx22.mine_and_slash.world_gen.structures.Random1ChunkDunStructure;
import com.robertx22.mine_and_slash.world_gen.structures.TowerStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.AtSurfaceWithChance;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldGenRegisters {

    public static final int SMALL_DECO_CHANCE = 50;
    public static final ConfiguredFeature randomSurfaceChest = create(new RandomSurfaceEggFeature(NoFeatureConfig::deserialize), new AtSurfaceWithChance(ChanceConfig::deserialize), new ChanceConfig(1000));
    public static final ConfiguredFeature smallRandomSurfaceDecoration = createSmallSurfaceDeco(new RandomSurfaceDecoration(NoFeatureConfig::deserialize));
    public static final ConfiguredFeature smallRandomSurfaceTreasure = Biome.createDecoratedFeature(new RandomSurfaceTreasure(NoFeatureConfig::deserialize), IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(400));

    public static Structure<NoFeatureConfig> towerStructure = null;
    public static Structure<NoFeatureConfig> floatingIslandStructure = null;
    public static Structure<NoFeatureConfig> dungeon0Structure = null;

    public static void register() {

        System.out.println("Registering Mine and Slash Map World Gen");

        registerStructure(towerStructure);

        for (Biome biome : ForgeRegistries.BIOMES) { // this works!

            // only register world gen where it can actually be used
            if (SlashRegistry.WorldProviders()
                    .getAll()
                    .values()
                    .stream()
                    .filter(iwp -> iwp.getBiome().equals(biome))
                    .collect(Collectors.toList())
                    .size() > 0) {

                add(biome, randomSurfaceChest);
                add(biome, smallRandomSurfaceDecoration);
                add(biome, smallRandomSurfaceTreasure);

                biome.addStructure(towerStructure, IFeatureConfig.NO_FEATURE_CONFIG);
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(towerStructure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

                biome.addStructure(floatingIslandStructure, IFeatureConfig.NO_FEATURE_CONFIG);
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(floatingIslandStructure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

                biome.addStructure(dungeon0Structure, IFeatureConfig.NO_FEATURE_CONFIG);
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Biome.createDecoratedFeature(dungeon0Structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

            }

        }

    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {

        towerStructure = registerStructure(new TowerStructure(NoFeatureConfig::deserialize));
        floatingIslandStructure = registerStructure(new FloatingIslandStructure(NoFeatureConfig::deserialize));
        dungeon0Structure = registerStructure(new Random1ChunkDunStructure(NoFeatureConfig::deserialize));

        event.getRegistry().register(towerStructure);
        event.getRegistry().register(floatingIslandStructure);
        event.getRegistry().register(dungeon0Structure);

    }

    private static <C extends IFeatureConfig, F extends Structure<C>> F registerStructure(
            F value) {

        Feature.STRUCTURES.put(value.getStructureName(), value);

        registerStruc(value.getStructureName(), value);

        return (F) (Registry.<Feature<?>>register(Registry.FEATURE, value.getStructureName(), value));

    }

    private static Structure<?> registerStruc(String id, Structure<?> structure) {
        return (Structure) Registry.register(Registry.STRUCTURE_FEATURE, id.toLowerCase(Locale.ROOT), structure);
    }

    public static void add(Biome biome, ConfiguredFeature comp) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, comp);
    }

    public static ConfiguredFeature createSmallSurfaceDeco(Feature feature) {

        return Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(SMALL_DECO_CHANCE));
    }

    public static <F extends IFeatureConfig, D extends IPlacementConfig> ConfiguredFeature<F> create(
            Feature feature, Placement<D> basePlacementIn, D placementConfig) {

        return Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, basePlacementIn, placementConfig);
    }

}
