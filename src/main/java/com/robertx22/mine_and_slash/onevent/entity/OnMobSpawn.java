package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.entities.IBossMob;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.HashSet;

public class OnMobSpawn {

    @SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

        if (event.getWorld().isRemote) {
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (event.getEntity() instanceof PlayerEntity) {
            return;
        }

        LivingEntity entity = (LivingEntity) event.getEntity();

        setupNewMobOnSpawn(entity);

        if (WorldUtils.isMapWorldClass(entity.world)) {
            if (entity instanceof MobEntity) {
                MobEntity mob = (MobEntity) entity;
                mob.enablePersistence();
                setupMobGoals((MobEntity) entity);
            }
        }

    }

    public static void setupNewMobOnSpawn(LivingEntity entity) {

        if (entity.world.isRemote) {
            throw new RuntimeException("Don't run this code on client!");
        }

        UnitData endata = Load.Unit(entity);

        if (endata != null) {

            if (endata.getUnit() != null) {
                endata.getUnit()
                    .removeUnregisteredStats();
            }

            endata.setType(entity);

            PlayerEntity nearestPlayer = null;
            WorldMapCap.IWorldMapData mapData = Load.world(entity.world);

            nearestPlayer = PlayerUtils.nearestPlayer((ServerWorld) entity.world, entity);

            if (endata.needsToBeGivenStats()) {
                Unit unit = Mob(entity, endata, mapData, nearestPlayer);
            } else {
                if (endata.getUnit() == null) {
                    endata.setUnit(new Unit(), entity);
                }

                endata.getUnit()
                    .initStats(); // give new stats to mob on spawn
                endata.forceRecalculateStats(entity);

            }

            if (entity instanceof IBossMob) {
                endata.setRarity(IRarity.Boss);
            }
        }
    }

    public static void setupMobGoals(MobEntity en) {

        int count = en.goalSelector.goals.size();

        for (PrioritizedGoal x : new HashSet<>(en.goalSelector.goals)) {
            Goal g = x.getGoal();

            if (g instanceof RandomWalkingGoal
                || g instanceof BreakDoorGoal) {
                en.goalSelector.removeGoal(g);
            }
        }

    }

    public static Unit Mob(LivingEntity entity, UnitData data,
                           WorldMapCap.IWorldMapData mapData, @Nullable PlayerEntity nearestPlayer) {

        Unit mob = new Unit();
        mob.initStats();

        UnitData endata = Load.Unit(entity);

        if (WorldUtils.isMapWorldClass(entity.world)) {
            endata.setTier(mapData.getTier(entity.getPosition(), entity.world));
        }

        endata.SetMobLevelAtSpawn(mapData, entity, nearestPlayer);

        int rar = mob.randomRarity(entity, endata);
        endata.setRarity(rar);

        MobRarity rarity = Rarities.Mobs.get(rar);
        mob.setRandomMobAffixes(rarity);

        endata.setUnit(mob, entity);

        mob.recalculateStats(entity, endata, endata.getLevel(), null);

        return mob;

    }

}
