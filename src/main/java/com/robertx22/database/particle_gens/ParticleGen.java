package com.robertx22.database.particle_gens;

import java.util.Random;

public abstract class ParticleGen {

    public Random rand = new Random();

    public abstract void Summon(double x, double y, double z, double xVel, double yVel, double zVel, double radius,
	    int amount);

    public abstract String Name();
}
