package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.onevent.ontick.OnBossTick;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.stat_calculation.MobStatUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.HashMap;

public class OnMobSpawn {

    @SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

        if (event.getWorld().isRemote) {
            return;
        }

        if (event.getEntity() instanceof PlayerEntity) {
            if (!event.getWorld().isRemote) {
                MMORPG.syncMapData((ServerPlayerEntity) event.getEntity());
            }
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entity = (LivingEntity) event.getEntity();

        UnitData endata = Load.Unit(entity);

        if (endata.getUnit() != null) {
            endata.getUnit()
                .removeUnregisteredStats();
        }

        if (endata != null) {

            BossCap.IBossData boss = Load.boss(entity);
            boss.onMobCreation(entity);

            if (boss.isBoss()) {
                OnBossTick.bossList.add(entity);
            }

            endata.setType(entity);

            PlayerEntity nearestPlayer = null;
            WorldMapCap.IWorldMapData mapData = Load.world(entity.world);

            nearestPlayer = PlayerUtils.nearestPlayer((ServerWorld) entity.world, entity);

            if (endata.needsToBeGivenStats()) {
                Unit unit = Mob(entity, endata, boss, mapData, nearestPlayer);
            } else {
                if (endata.getUnit() == null) {
                    endata.setUnit(new Unit(), entity);
                }

                boolean broken = endata.getUnit().statusEffects.values()
                    .stream()
                    .anyMatch(x -> x.isBroken());
                if (broken) {
                    endata.getUnit().statusEffects = new HashMap<>();
                }

                endata.getUnit()
                    .initStats(); // give new stats to mob on spawn
                endata.forceRecalculateStats(entity);

            }

            if (boss.isBoss()) {
                endata.setRarity(IRarity.Boss);
            }
        }

    }

    public static Unit Mob(LivingEntity entity, UnitData data, BossCap.IBossData boss,
                           WorldMapCap.IWorldMapData mapData, @Nullable PlayerEntity nearestPlayer) {

        Unit mob = new Unit();
        mob.initStats();

        UnitData endata = Load.Unit(entity);

        if (WorldUtils.isMapWorldClass(entity.world)) {
            endata.setTier(mapData.getTier());
        }

        endata.SetMobLevelAtSpawn(mapData, entity, nearestPlayer);
        endata.setRarity(mob.randomRarity(entity, endata, boss));

        MobStatUtils.AddRandomMobStatusEffects(entity, mob, Rarities.Mobs.get(endata.getRarity()));

        endata.setUnit(mob, entity);

        mob.recalculateStats(entity, endata, endata.getLevel());

        return mob;

    }

}
