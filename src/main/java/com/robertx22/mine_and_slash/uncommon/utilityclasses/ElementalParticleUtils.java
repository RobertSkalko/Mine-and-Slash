package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.particle_gens.AoeProjectileParticleGen;
import com.robertx22.mine_and_slash.database.particle_gens.NovaParticleGen;
import com.robertx22.mine_and_slash.uncommon.interfaces.IColor;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ElementalParticleUtils {

    public static void SpawnNovaParticle(IColor icolor, Entity entity, double radius,
                                         int amount) {

        String name = new NovaParticleGen().Name();

        ParticleUtils.spawnParticleGenerator(entity, name, entity.posX, entity.posY + entity
                .getHeight() / 2, entity.posZ, 1, 1, 1, radius, amount, icolor);
    }

    public static void SpawnAoeParticle(IColor icolor, Entity entity, double radius,
                                        int amount) {

        String name = new AoeProjectileParticleGen().Name();

        ParticleUtils.spawnParticleGenerator(entity, name, entity.posX, entity.posY, entity.posZ, 1, 1, 1, radius, amount, icolor);
    }

    public static void SpawnAoeParticle(IColor icolor, World world, double x, double y,
                                        double z, double radius, int amount) {

        String name = new AoeProjectileParticleGen().Name();

        ParticleUtils.spawnParticleGenerator(world, name, x, y, z, 1, 1, 1, radius, amount, icolor);
    }

}
