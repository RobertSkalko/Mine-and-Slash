package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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

    public static void spawnParticles(ParticleType particle, LivingEntity en, int amount) {

        ParticleEnum.sendToClients(en, new ParticlePacketData(en.getPosition(), ParticleEnum.AOE).radius(1)
                .type(particle)
                .amount(amount));

    }

}
