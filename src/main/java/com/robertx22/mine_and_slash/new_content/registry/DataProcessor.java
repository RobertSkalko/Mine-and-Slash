package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.data_processors.ChunkProcessData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.WeightedWrapper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.ArrayList;

public abstract class DataProcessor {

    public DataProcessor(String data) {
        this.data = data;
        this.type = Type.EQUALS;
    }

    public DataProcessor(String data, Type type) {
        this.data = data;
        this.type = type;
    }

    public enum Type {
        EQUALS, CONTAINS

    }

    Type type;

    public String data;

    public final boolean process(String key, BlockPos pos, IWorld world, ChunkProcessData chunkData) {
        if (type == Type.EQUALS && this.data.equals(key)) {
            processImplementation(key, pos, world, chunkData);
            return true;
        } else if (type == Type.CONTAINS && key.contains(this.data)) {
            processImplementation(key, pos, world, chunkData);
            return true;
        }

        return false;
    }

    public EntityType<? extends MobEntity> randomMob() {
        return (EntityType<? extends MobEntity>) RandomUtils.weightedRandom(getMobList()).object;

    }

    private ArrayList<WeightedWrapper<EntityType<? extends MobEntity>>> mobList = new ArrayList<>();

    public ArrayList<WeightedWrapper<EntityType<? extends MobEntity>>> getMobList() {

        if (mobList.isEmpty()) {
            mobList.add(new WeightedWrapper(EntityType.ZOMBIE, 1200));
            mobList.add(new WeightedWrapper(EntityType.WITHER_SKELETON, 50));
            mobList.add(new WeightedWrapper(EntityType.SKELETON, 400));
            mobList.add(new WeightedWrapper(EntityType.BLAZE, 15));
            mobList.add(new WeightedWrapper(EntityType.MAGMA_CUBE, 10));
            mobList.add(new WeightedWrapper(EntityType.ENDERMITE, 5));
            mobList.add(new WeightedWrapper(EntityType.CAVE_SPIDER, 200));
            mobList.add(new WeightedWrapper(EntityType.SPIDER, 300));
            mobList.add(new WeightedWrapper(EntityType.PILLAGER, 500));
            mobList.add(new WeightedWrapper(EntityType.WITCH, 100));
            mobList.add(new WeightedWrapper(EntityType.VINDICATOR, 25));
            mobList.add(new WeightedWrapper(EntityType.SLIME, 5));
            mobList.add(new WeightedWrapper(EntityType.EVOKER, 2));
            mobList.add(new WeightedWrapper(EntityType.ILLUSIONER, 30));
            mobList.add(new WeightedWrapper(EntityType.HUSK, 500));
            mobList.add(new WeightedWrapper(EntityType.RAVAGER, 10));
            mobList.add(new WeightedWrapper(EntityType.STRAY, 300));

        }

        return mobList;

    }

    public abstract void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data);

}
