package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.world_gen.features.RandomSurfaceDecoration;
import com.robertx22.mine_and_slash.world_gen.features.RandomSurfaceEggFeature;
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

    public static final ConfiguredFeature randomSurfaceChest = create(
            new RandomSurfaceEggFeature(NoFeatureConfig::deserialize),
            new AtSurfaceWithChance(ChanceConfig::deserialize), new ChanceConfig(1000)
    );

    public static final ConfiguredFeature smallRandomSurfaceDecoration = create(
            new RandomSurfaceDecoration(NoFeatureConfig::deserialize),
            new AtSurfaceWithChance(ChanceConfig::deserialize), new ChanceConfig(40)
    );

    public static final ConfiguredFeature smallRandomSurfaceTreasure = create(
            new RandomSurfaceDecoration(NoFeatureConfig::deserialize),
            new AtSurfaceWithChance(ChanceConfig::deserialize), new ChanceConfig(300)
    );

    public static Structure<NoFeatureConfig> towerStructure = null;
    public static Structure<NoFeatureConfig> dungeon0Structure = null;

    private static void register() {

        System.out.println("Registering Mine and Slash Map World Gen");

        OreGenRegister.register();

        registerStructure(towerStructure);

        for (Biome biome : ForgeRegistries.BIOMES) { // this works!

            // only registerForgeConfigs world getMap where it can actually be used
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

                ConfiguredFeature<?, ?> tower0 = towerStructure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                        .func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG));
                ConfiguredFeature<?, ?> dungeon0 = dungeon0Structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                        .func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG));

                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, tower0);
                biome.addStructure(towerStructure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, dungeon0);
                biome.addStructure(dungeon0Structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

            }

        }

    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {

        towerStructure = registerStructure(new TowerStructure(NoFeatureConfig::deserialize));
        dungeon0Structure = registerStructure(new Random1ChunkDunStructure(NoFeatureConfig::deserialize));

        event.getRegistry().register(towerStructure);
        event.getRegistry().register(dungeon0Structure);

        register();

    }

    private static <C extends IFeatureConfig, F extends Structure<C>> F registerStructure(F value) {

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

    public static <FC extends Feature<C>, C extends IFeatureConfig, P extends IPlacementConfig> ConfiguredFeature<C,
            FC> create(
            Feature feature, Placement<P> place, P placeConfig) {

        return feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                .func_227228_a_(place.func_227446_a_(placeConfig));

    }

}
