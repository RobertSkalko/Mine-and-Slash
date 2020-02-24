package com.robertx22.mine_and_slash.world_gen.processors;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpawnerRandProcessor extends StructureProcessor {

    public SpawnerRandProcessor() {

    }

    public SpawnerRandProcessor(Dynamic<?> dynamic) {
    }

    static List<EntityType<? extends MobEntity>> possibleMobs = new ArrayList<>();

    public static List<EntityType<? extends MobEntity>> getPossibleMobs(World world) {
        if (possibleMobs.isEmpty()) {
            setupList(world);
        }

        return possibleMobs;
    }

    static void setupList(World world) {

        if (possibleMobs.isEmpty()) {

            possibleMobs = ForgeRegistries.ENTITIES.getValues()
                .stream()
                .filter(x -> {
                    Entity en = x.create(world);
                    return EntityTypeUtils.isMob(en) && en.isNonBoss();
                })
                .map(x -> ((EntityType<? extends MobEntity>) x))
                .collect(Collectors.toList());
        }

    }

    @Nullable
    @Override
    public Template.BlockInfo process(IWorldReader iWorldReader, BlockPos blockPos, Template.BlockInfo blockInfo,
                                      Template.BlockInfo blockInfo1, PlacementSettings placementSettings) {

        try {
            Block block = blockInfo1.state.getBlock();

            if (block == Blocks.SPAWNER) {

                if (RandomUtils.roll(10)) {

                    if (possibleMobs.isEmpty()) {
                        World world = MapManager.getWorld(iWorldReader.getDimension()
                            .getType());
                        setupList(world);
                    }

                    EntityType<?> type = RandomUtils.randomFromList(possibleMobs);

                    ((CompoundNBT) blockInfo1.nbt.get("SpawnData")).putString("id", type.getRegistryName()
                        .toString());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blockInfo1;

    }

    @Override
    protected IStructureProcessorType getType() {
        return IStructureProcessorType.RULE;
    }

    @Override
    protected <T> Dynamic<T> serialize0(DynamicOps<T> dynamicOps) {
        return new Dynamic(dynamicOps, dynamicOps.createMap(ImmutableMap.of()));
    }

}
