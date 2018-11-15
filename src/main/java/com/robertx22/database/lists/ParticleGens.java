package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.particle_gens.AoeProjectileParticleGen;
import com.robertx22.database.particle_gens.ParticleGen;

public class ParticleGens {
	public static HashMap<String, ParticleGen> All = new HashMap<String, ParticleGen>() {
		{
			{
				put(new AoeProjectileParticleGen().Name(), new AoeProjectileParticleGen());

			}
		}
	};
}
