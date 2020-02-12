package com.robertx22.mine_and_slash.database.map_events.impl;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.saveclasses.MapEventsData;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class PhantomSpawnsEvent extends MapEvent {

    private PhantomSpawnsEvent() {
    }

    public static PhantomSpawnsEvent getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void onMinutePassed(World world, MapEventsData.Data data) {

        if (data.minRem != minutesEventLasts()) { // take a min to spawn them

            announceEvent(world, new StringTextComponent(TextFormatting.LIGHT_PURPLE + "A batch of Phantoms arrived."));

            for (int i = 0; i < 30; i++) {
                BlockPos p = randomPosNearPlayer(world).up(15);
                summonMinion(EntityType.PHANTOM, world, p);
            }
        }
    }

    @Override
    public void onActivate(World world) {

        if (!world.getPlayers().isEmpty()) {
            announceEvent(world, new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Phantoms are approaching!"));
        }

    }

    @Override
    public int minutesEventLasts() {
        return 5;
    }

    @Override
    public int Weight() {
        return super.Weight() / 4;
    }

    @Override
    public String GUID() {
        return "phantom_spawns";
    }

    private static class SingletonHolder {
        private static final PhantomSpawnsEvent INSTANCE = new PhantomSpawnsEvent();
    }
}