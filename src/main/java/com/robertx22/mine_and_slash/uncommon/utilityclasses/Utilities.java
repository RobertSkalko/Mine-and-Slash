package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.UUID;

public final class Utilities {

    @Nullable
    public static LivingEntity getLivingEntityByUUID(World world, @Nullable UUID id) {

        Entity en = getEntityByUUID(world, id);

        if (en instanceof LivingEntity) {
            return (LivingEntity) en;
        } else {
            return null;
        }

    }

    @Nullable
    public static Entity getEntityByUUID(World world, @Nullable UUID id) {

        if (id == null)
            return null;

        if (world.isRemote) {
            return ClientOnly.getEntityByUUID(world, id);
        } else {
            return ServerOnly.getEntityByUUID(world, id);
        }

    }

    public static Vec3d getEndOfLook(LivingEntity entity, double distance) {
        return entity.getEyePosition(0.5F).add(entity.getLookVec().scale(distance));
    }

    public static void spawnParticlesForTesting(AxisAlignedBB aabb, World world) {

        for (double x = aabb.minX; x < aabb.maxX; x++) {
            for (double y = aabb.minY; y < aabb.maxY; y++) {
                for (double z = aabb.minZ; z < aabb.maxZ; z++) {

                    for (int i = 0; i < 10; i++) {
                        ((ServerWorld) world).spawnParticle(
                                ParticleTypes.HAPPY_VILLAGER, x, y, z, 0, 0.0D, 0.0D, 0.0D, 0F);
                    }
                }
            }
        }

    }

    public static double getPlayerEyesPos(LivingEntity player) {
        return player.getBoundingBox().minY + player.getEyeHeight();
    }

}
