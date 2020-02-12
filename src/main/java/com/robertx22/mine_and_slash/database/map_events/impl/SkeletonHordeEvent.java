package com.robertx22.mine_and_slash.database.map_events.impl;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SkeletonHordeEvent extends MapEvent {

    private SkeletonHordeEvent() {
    }

    public static SkeletonHordeEvent getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void onActivate(World world) {

        if (!world.getPlayers().isEmpty()) {

            BlockPos p = randomPosNearPlayer(world);

            announceEvent(
                    world, new StringTextComponent(TextFormatting.LIGHT_PURPLE + "A Skeleton Horde has Appeared!"));
            summonBeacon(p, world);

            com.robertx22.mine_and_slash.database.bosses.base.Boss boss = SlashRegistry.Bosses().random();

            Entity bossMob = summonBoss(EntityType.WITHER_SKELETON, world, p, boss);

            summonMinions(EntityType.SKELETON, 5, world, p);

        }

    }

    @Override
    public int minutesEventLasts() {
        return 0;
    }

    @Override
    public String GUID() {
        return "skeleton_horde";
    }

    private static class SingletonHolder {
        private static final SkeletonHordeEvent INSTANCE = new SkeletonHordeEvent();
    }
}
