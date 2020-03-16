package com.robertx22.mine_and_slash.new_content.data_processors.bases;

import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpawnedMob implements IWeighted {

    private static List<SpawnedMob> all = new ArrayList<>();

    public SpawnedMob(EntityType<? extends MobEntity> type, int weight) {
        this.type = type;
        this.weight = weight;
    }

    public static SpawnedMob random(DungeonRoom room) {
        return RandomUtils.weightedRandom(getAll().stream()
            .filter(x -> room.canSpawnMob(x))
            .collect(Collectors.toList()));
    }

    public static List<SpawnedMob> getAll() {

        if (all.isEmpty()) {
            all.add(new SpawnedMob(EntityType.ZOMBIE, 1200));
            all.add(new SpawnedMob(EntityType.WITHER_SKELETON, 50));
            all.add(new SpawnedMob(EntityType.SKELETON, 400).setRanged());
            all.add(new SpawnedMob(EntityType.BLAZE, 20).setRanged()
                .setFire());
            all.add(new SpawnedMob(EntityType.MAGMA_CUBE, 10).setFire());
            all.add(new SpawnedMob(EntityType.ENDERMITE, 5));
            all.add(new SpawnedMob(EntityType.CAVE_SPIDER, 200).setSpider());
            all.add(new SpawnedMob(EntityType.SPIDER, 300).setSpider());
            all.add(new SpawnedMob(EntityType.PILLAGER, 500).setRanged());
            all.add(new SpawnedMob(EntityType.WITCH, 100).setRanged());
            all.add(new SpawnedMob(EntityType.VINDICATOR, 25));
            all.add(new SpawnedMob(EntityType.SLIME, 5));
            all.add(new SpawnedMob(EntityType.EVOKER, 2));
            all.add(new SpawnedMob(EntityType.ILLUSIONER, 30));
            all.add(new SpawnedMob(EntityType.HUSK, 500));
            all.add(new SpawnedMob(EntityType.RAVAGER, 10).setCanBeBoss(false));
            all.add(new SpawnedMob(EntityType.STRAY, 300).setRanged());
        }

        return all;

    }

    public EntityType<? extends MobEntity> type;
    public boolean isRanged = false;
    public boolean isSpider = false;
    public boolean isFire = false;

    int weight = 1000;
    public boolean canBeBoss = true;

    public SpawnedMob setRanged() {
        this.isRanged = true;
        return this;
    }

    public SpawnedMob setSpider() {
        this.isSpider = true;
        return this;
    }

    public SpawnedMob setFire() {
        this.isFire = true;
        return this;
    }

    public SpawnedMob setCanBeBoss(Boolean bool) {
        this.canBeBoss = bool;
        return this;
    }

    @Override
    public int Weight() {
        return weight;
    }
}
