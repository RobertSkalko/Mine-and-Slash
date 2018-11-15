package com.robertx22.database.particle_gens;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;

public class AoeProjectileParticleGen extends ParticleGen {

	int radius = 3;

	@Override
	public void Summon(double x, double y, double z, double xVel, double yVel, double zVel) {
		for (int i = 0; i < 100; i++) {

			Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.REDSTONE,
					x + (this.rand.nextDouble() * 4 - 2) * radius / 2,
					y + (this.rand.nextDouble() * 3 - 2) * radius / 2,
					z + (this.rand.nextDouble() * 4 - 2) * radius / 2, xVel, yVel, zVel);
		}
	}

	@Override
	public String Name() {
		return "AoeProjectileParticleGen";
	}

}
