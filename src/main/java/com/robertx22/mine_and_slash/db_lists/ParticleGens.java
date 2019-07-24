package com.robertx22.mine_and_slash.db_lists;

import com.robertx22.mine_and_slash.database.particle_gens.AoeProjectileParticleGen;
import com.robertx22.mine_and_slash.database.particle_gens.NovaParticleGen;
import com.robertx22.mine_and_slash.database.particle_gens.ParticleGen;

import java.util.HashMap;

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
