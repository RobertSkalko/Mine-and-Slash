package com.robertx22.mine_and_slash.new_content.registry;

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
    }

    String data;

    public final boolean process(String data, BlockPos pos, IWorld world) {
        if (this.data.equals(data)) {
            processImplementation(pos, world);
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
            mobList.add(new WeightedWrapper(EntityType.ZOMBIE, 1000));
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
            mobList.add(new WeightedWrapper(EntityType.RAVAGER, 40));

        }

        return mobList;

    }

    public abstract void processImplementation(BlockPos pos, IWorld world);

}
