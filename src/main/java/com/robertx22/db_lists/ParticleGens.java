package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.particle_gens.AoeProjectileParticleGen;
import com.robertx22.database.particle_gens.NovaParticleGen;
import com.robertx22.database.particle_gens.ParticleGen;

public class ParticleGens {
    public static HashMap<String, ParticleGen> All = new HashMap<String, ParticleGen>() {
	{
	    {
		put(new AoeProjectileParticleGen().Name(), new AoeProjectileParticleGen());
		put(new NovaParticleGen().Name(), new NovaParticleGen());

	    }
	}
    };
}
