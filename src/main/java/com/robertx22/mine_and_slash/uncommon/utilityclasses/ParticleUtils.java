package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.ParticleGenPacket;
import com.robertx22.mine_and_slash.uncommon.interfaces.IColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

    public static void spawnParticles(IParticleData particle, LivingEntity en,
                                      int amount) {
        for (int i = 0; i < amount; i++) {
            double d0 = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
            // Apparently the client side spawns the particles 1 block higher than it
            // should... hence the -
            // 0.5F.
            double d1 = (double) ((float) Utilities.getPlayerEyesPos(en) - 0.5F + en.world.rand
                    .nextFloat());
            double d2 = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

            if (en.world.isRemote) {
                en.world.addParticle(particle, true, d0, d1, d2, 0, 48 + en.world.rand.nextInt(12), 1.0f);
            } else {
                ((ServerWorld) en.world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, d0, d1, d2, 10, 0.0D, 0.0D, 0.0D, (double) 1F);
            }

        }
    }

    public static void spawnParticleGenerator(Entity source, String name, double x,
                                              double y, double z, double xVel,
                                              double yVel, double zVel, double radius,
                                              int amount, IColor icolor) {

        ParticleGenPacket packet = new ParticleGenPacket(name, x, y, z, xVel, yVel, zVel, radius, amount, icolor);

        MMORPG.sendToTracking(packet, source);

    }

    public static void spawnParticleGenerator(World world, String name, double x,
                                              double y, double z, double xVel,
                                              double yVel, double zVel, double radius,
                                              int amount, IColor icolor) {

        ParticleGenPacket packet = new ParticleGenPacket(name, x, y, z, xVel, yVel, zVel, radius, amount, icolor);

        MMORPG.sendToTracking(packet, new BlockPos(x, y, z), world);

    }

}
