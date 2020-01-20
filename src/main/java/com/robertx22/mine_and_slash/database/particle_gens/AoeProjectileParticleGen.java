package com.robertx22.mine_and_slash.database.particle_gens;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import net.minecraft.util.math.Vec3d;

public class AoeProjectileParticleGen extends ParticleGen {

    @Override
    public void Summon(double x, double y, double z, double radius, int amount,
                       Elements.RGB color) {
        for (int i = 0; i < amount; i++) {

            Vec3d r = GeometryUtils.getRandomPosInRadiusCircle(x, y, z, (float) radius);

            this.spawnRedstone(color, r.x, r.y, r.z);

        }
    }

    @Override
    public String Name() {
        return "AoeProjectileParticleGen";
    }

}
