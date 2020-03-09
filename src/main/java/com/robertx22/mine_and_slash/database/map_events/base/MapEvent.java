package com.robertx22.mine_and_slash.database.map_events.base;

import com.robertx22.mine_and_slash.database.rarities.mobs.EpicMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.LegendaryMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.MythicalMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.RareMob;
import com.robertx22.mine_and_slash.new_content.registry.MobPotionEffects;
import com.robertx22.mine_and_slash.onevent.entity.OnMobSpawn;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.MapEventsData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Arrays;

public abstract class MapEvent implements ISlashRegistryEntry<MapEvent> {

    public abstract void onActivate(World worlds);

    public abstract int minutesEventLasts();

    public void onMinutePassed(World world, MapEventsData.Data data) {

    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.MAP_EVENT;
    }

    public static void announceEvent(World world, ITextComponent comp) {
        world.getPlayers()
            .forEach(x -> x.sendMessage(comp));
    }

    public static <T extends MobEntity> void summonMinions(EntityType<T> type, int amount, IWorld world, BlockPos p) {
        for (int i = 0; i < amount; i++) {
            summonMinion(type, world, p);
        }
    }

    public static <T extends MobEntity> T summonBoss(EntityType<T> type, IWorld world, BlockPos p,
                                                     com.robertx22.mine_and_slash.database.bosses.base.Boss boss) {
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

    public static <T extends MobEntity> T summonElite(EntityType<T> type, IWorld world, BlockPos p) {
        Vec3d vec = new Vec3d(p);
        vec = vec.add(0.5F, 0, 0.5F);

        T elite = (T) type.create(world.getWorld());
        elite.onInitialSpawn(world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, null);
        elite.setPosition(vec.getX(), vec.getY(), vec.getZ());

        OnMobSpawn.setupNewMobOnSpawn(elite);

        Load.Unit(elite)
            .setRarity(RandomUtils.weightedRandom(Arrays.asList(MythicalMob.getInstance(), LegendaryMob.getInstance(), EpicMob.getInstance(), RareMob.getInstance()))
                .Rank());

        if (RandomUtils.roll(25)) {
            elite.addPotionEffect(new EffectInstance(MobPotionEffects.getRandom(), Integer.MAX_VALUE, RandomUtils.RandomRange(1, 3)));
        }

        if (elite instanceof SlimeEntity) {
            SlimeEntity slime = (SlimeEntity) elite;
            CompoundNBT nbt = slime.serializeNBT();
            nbt.putInt("Size", 10);
            slime.deserializeNBT(nbt);
        }

        world.addEntity(elite);

        return elite;

    }

    public static BlockPos randomPosNearPlayer(World world) {
        BlockPos pos = world.getPlayers()
            .get(0)
            .getPosition();
        pos = new BlockPos(pos.getX() + RandomUtils.RandomRange(-50, 50), pos.getY(),
            pos.getZ() + RandomUtils.RandomRange(-50, 50)
        );
        pos = WorldUtils.getSurface(world, pos)
            .up();

        return pos;

    }
}
