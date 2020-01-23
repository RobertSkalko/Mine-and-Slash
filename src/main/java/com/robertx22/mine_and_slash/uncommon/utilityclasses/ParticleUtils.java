package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;

public class ParticleUtils {

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
            double d0 = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
            // Apparently the client side spawns the particles 1 block higher than it
            // should... hence the -
            // 0.5F.
            double d1 = (double) ((float) Utilities.getPlayerEyesPos(en) - 0.5F + en.world.rand.nextFloat());
            double d2 = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

            if (en.world.isRemote) {
                en.world.addParticle(particle, true, d0, d1, d2, 0, 48 + en.world.rand.nextInt(12), 1.0f);
            } else {
                ((ServerWorld) en.world).spawnParticle(particle, d0, d1, d2, 10, 0.0D, 0.0D, 0.0D, (double) 1F);
            }

        }
    }

}
