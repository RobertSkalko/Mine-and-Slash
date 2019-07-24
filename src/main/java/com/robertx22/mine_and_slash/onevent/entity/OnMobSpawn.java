package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnMobSpawn {

    @SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

        if (event.getWorld().isRemote) {
            return;
        }
        if (event.getEntity() instanceof PlayerEntity) {
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entity = (LivingEntity) event.getEntity();

        UnitData endata = Load.Unit(entity);

        if (endata != null) {

            PlayerEntity nearestPlayer = null;

            if (WorldUtils.isMapWorld(entity.world)) {
                nearestPlayer = PlayerUtils.nearestPlayer((ServerWorld) entity.world, entity);
            }

            if (endata.needsToBeGivenStats()) {
                Unit unit = Unit.Mob(entity, nearestPlayer);
                endata.forceSetUnit(unit);
            } else {
                if (endata.getUnit() == null) {
                    endata.setUnit(new Unit(), entity);
                }

                endata.getUnit().InitMobStats(); // give new stats to mob on spawn
                endata.forceRecalculateStats(entity);
            }
        }

    }

}
