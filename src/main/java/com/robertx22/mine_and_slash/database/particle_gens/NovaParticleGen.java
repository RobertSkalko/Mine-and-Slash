package com.robertx22.mine_and_slash.database.particle_gens;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class NovaParticleGen extends ParticleGen {

    @Override
    public void Summon(double x, double y, double z, double radius, int amount,
                       Elements.RGB color) {

        for (int i = 0; i < amount; i++) {

            double u = Math.random();
            double v = Math.random();
            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);
            double xpos = x + (radius * Math.sin(phi) * Math.cos(theta));
            double ypos = y;
            double zpos = z + (radius * Math.cos(phi));

            this.spawnRedstone(color, xpos, ypos, zpos);

        }
    }

    @Override
    public String Name() {
        return "NovaParticleGen";
    }

}
