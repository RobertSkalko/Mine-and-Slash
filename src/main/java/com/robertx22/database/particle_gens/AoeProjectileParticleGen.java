package com.robertx22.database.particle_gens;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;

public class AoeProjectileParticleGen extends ParticleGen {

    @Override
    public void Summon(double x, double y, double z, double xVel, double yVel, double zVel, double radius, int amount) {
	for (int i = 0; i < amount; i++) {

	    double u = Math.random();
	    double v = Math.random();
	    double theta = 2 * Math.PI * u;
	    double phi = Math.acos(2 * v - 1);
	    double xpos = x + (radius * Math.sin(phi) * Math.cos(theta));
	    double ypos = y + (radius * Math.sin(phi) * Math.sin(theta));
	    double zpos = z + (radius * Math.cos(phi));

	    Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.REDSTONE, xpos, ypos, zpos, xVel, yVel,
		    zVel);

	}
    }

    @Override
    public String Name() {
	return "AoeProjectileParticleGen";
    }

}
