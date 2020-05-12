package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.mobs.EpicMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.LegendaryMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.RareMob;
import com.robertx22.mine_and_slash.new_content.registry.MobPotionEffects;
import com.robertx22.mine_and_slash.onevent.entity.OnMobSpawn;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;

import java.util.Arrays;

public class MobSpawnUtils {

    public static <T extends MobEntity> void summonMinions(EntityType<T> type, int amount, IWorld world, BlockPos p) {
        for (int i = 0; i < amount; i++) {
            summonMinion(type, world, p);
        }
    }

    public static <T extends MobEntity> T summonBoss(EntityType<T> type, IWorld world, BlockPos p
    ) {
        Vec3d vec = new Vec3d(p);
        vec = vec.add(0.5F, 0, 0.5F);

        T bossEntity = (T) type.create(world.getWorld());
        bossEntity.onInitialSpawn(world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, null);
        bossEntity.setPosition(vec.getX(), vec.getY(), vec.getZ());

        OnMobSpawn.setupNewMobOnSpawn(bossEntity);

        Load.boss(bossEntity)
            .setBoss(boss);

        Load.Unit(bossEntity)
            .setRarity(IRarity.Boss);

        world.addEntity(bossEntity);

        return bossEntity;
    }

    public static <T extends MobEntity> T summonMinion(EntityType<T> type, IWorld world, BlockPos p) {
        Vec3d vec = new Vec3d(p);
        vec = vec.add(0.5F, 0, 0.5F);

        T minion = (T) type.create(world.getWorld());
        minion.onInitialSpawn(world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, null);
        minion.setPosition(vec.getX(), vec.getY(), vec.getZ());

        OnMobSpawn.setupNewMobOnSpawn(minion);

        world.addEntity(minion);

        return minion;
    }

    public static <T extends MobEntity> T summon(EntityType<T> type, IWorld world, BlockPos p, MobRarity rarity, boolean addPotion) {
        Vec3d vec = new Vec3d(p);
        vec = vec.add(0.5F, 0, 0.5F);

        T mob = (T) type.create(world.getWorld());
        mob.onInitialSpawn(world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, null);
        mob.setPosition(vec.getX(), vec.getY(), vec.getZ());

        OnMobSpawn.setupNewMobOnSpawn(mob);

        Load.Unit(mob)
            .setRarity(rarity.Rank());

        if (boss != null) {
            Load.boss(mob)
                .setBoss(SlashRegistry.Bosses()
                    .random());
            Load.Unit(mob)
                .setRarity(IRarity.Boss);
        }

        if (addPotion) {
            mob.addPotionEffect(new EffectInstance(MobPotionEffects.getRandom(), Integer.MAX_VALUE, RandomUtils.RandomRange(1, 3)));
        }

        world.addEntity(mob);

        return mob;
    }

    public static <T extends MobEntity> T summonElite(EntityType<T> type, IWorld world, BlockPos p) {
        Vec3d vec = new Vec3d(p);
        vec = vec.add(0.5F, 0, 0.5F);

        T elite = (T) type.create(world.getWorld());
        elite.onInitialSpawn(world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, null);
        elite.setPosition(vec.getX(), vec.getY(), vec.getZ());

        OnMobSpawn.setupNewMobOnSpawn(elite);

        Load.Unit(elite)
            .setRarity(RandomUtils.weightedRandom(Arrays.asList(LegendaryMob.getInstance(), EpicMob.getInstance(), RareMob.getInstance()))
                .Rank());

        if (RandomUtils.roll(25)) {
            elite.addPotionEffect(new EffectInstance(MobPotionEffects.getRandom(), Integer.MAX_VALUE, RandomUtils.RandomRange(1, 3)));
        }

        world.addEntity(elite);

        return elite;

    }

}
