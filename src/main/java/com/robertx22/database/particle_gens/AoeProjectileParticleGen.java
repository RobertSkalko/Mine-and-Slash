package com.robertx22.database.particle_gens;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;

public class AoeProjectileParticleGen extends ParticleGen {

    @Override
    public void Summon(double x, double y, double z, double xVel, double yVel, double zVel, double radius, int amount) {
	for (int i = 0; i < amount; i++) {

	    Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.REDSTONE,
		    x + (this.rand.nextDouble() * 2 - 1) * radius, y + (this.rand.nextDouble() * 2 - 1) * radius,
		    z + (this.rand.nextDouble() * 2 - 1) * radius, xVel, yVel, zVel);
	}
    }

    @Override
    public String Name() {
	return "AoeProjectileParticleGen";
    }

}
