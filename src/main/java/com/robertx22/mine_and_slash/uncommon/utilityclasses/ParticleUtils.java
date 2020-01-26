package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ParticleUtils {

    public static void spawn(IParticleData type, World world, Vec3d vec) {
        world.addParticle(type, vec.x, vec.y, vec.z, 0, 0, 0);
    }

    public static void spawnHealParticles(LivingEntity en, int amount) {

        spawnParticles(ParticleTypes.HEART, en, amount);

    }

    public static void spawnEnergyRestoreParticles(LivingEntity en, int amount) {

        spawnParticles(ParticleTypes.HAPPY_VILLAGER, en, amount);

    }

    public static void spawnManaRestoreParticles(LivingEntity en, int amount) {

        spawnParticles(ParticleTypes.BUBBLE, en, amount);

    }

    public static void spawnParticles(IParticleData particle, LivingEntity en, int amount) {
        for (int i = 0; i < amount; i++) {

            double x = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
            double y = (double) ((float) Utilities.getPlayerEyesPos(en) - 0.5F + en.world.rand.nextFloat());
            double z = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

            if (en.world.isRemote) {
                en.world.addParticle(particle, true, x, y, z, 0, 1, 1.0f);
            } else {
                ((ServerWorld) en.world).spawnParticle(particle, x, y, z, 0, 0.0D, 0.0D, 0.0D, (double) 1F);
            }

        }
    }

}
