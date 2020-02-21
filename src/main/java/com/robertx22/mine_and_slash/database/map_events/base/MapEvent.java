package com.robertx22.mine_and_slash.database.map_events.base;

import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.MapEventsData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class MapEvent implements ISlashRegistryEntry<MapEvent> {

    public abstract void onActivate(World worlds);

    public abstract int minutesEventLasts();

    public void onMinutePassed(World world, MapEventsData.Data data) {

    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.MAP_EVENT;
    }

    public static void summonBeacon(BlockPos pos, World world) {
        world.setBlockState(WorldUtils.getSurface(world, pos), BlockRegister.BEACON.get()
            .getDefaultState());
    }

    public static void announceEvent(World world, ITextComponent comp) {
        world.getPlayers()
            .forEach(x -> x.sendMessage(comp));
    }

    public static <T extends MobEntity> void summonMinions(EntityType<T> type, int amount, World world, BlockPos p) {
        for (int i = 0; i < amount; i++) {
            summonMinion(type, world, p);
        }
    }

    public static <T extends MobEntity> T summonBoss(EntityType<T> type, World world, BlockPos p,
                                                     com.robertx22.mine_and_slash.database.bosses.base.Boss boss) {
        T bossEntity = (T) type.create(world);
        bossEntity.onInitialSpawn(world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, null);
        bossEntity.setPosition(p.getX(), p.getY(), p.getZ());
        Load.boss(bossEntity)
            .setBoss(boss);

        world.addEntity(bossEntity);

        return bossEntity;
    }

    public static <T extends MobEntity> T summonMinion(EntityType<T> type, World world, BlockPos p) {
        T minion = (T) type.create(world);
        minion.onInitialSpawn(world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, null);
        minion.setPosition(p.getX(), p.getY(), p.getZ());
        world.addEntity(minion);

        return minion;
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
