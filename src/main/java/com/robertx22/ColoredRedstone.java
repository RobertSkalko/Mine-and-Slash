package com.robertx22;

import java.util.HashMap;

import com.robertx22.database.particle_gens.AoeProjectileParticleGen;
import com.robertx22.database.particle_gens.NovaParticleGen;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;

import net.minecraft.entity.Entity;

public class ColoredRedstone {

    static class Color {
	int x;
	int y;
	int z;

	public static HashMap<Elements, Color> BY_ELEMENT = new HashMap<Elements, Color>() {
	    {
		put(Elements.Fire, new Color(0, 0, 0));
		put(Elements.Water, new Color(-1, -1, 1));
		put(Elements.Thunder, new Color(0, 1, 0));
		put(Elements.Nature, new Color(-1, 1, 0));
		put(Elements.None, new Color(0, 0, 1));

	    }
	};

	public Color(int x, int y, int z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}

    }

    public static void SpawnNovaRedstone(Elements element, Entity entity, double radius, int amount) {

	Color color = Color.BY_ELEMENT.get(element);
	String name = new NovaParticleGen().Name();

	ParticleUtils.spawnParticleGenerator(entity, name, entity.posX, entity.posY + entity.height / 2, entity.posZ,
		color.x, color.y, color.z, radius, amount);
    }

    public static void SpawnAoeRedstone(Elements element, Entity entity, double radius, int amount) {

	Color color = Color.BY_ELEMENT.get(element);
	String name = new AoeProjectileParticleGen().Name();

	ParticleUtils.spawnParticleGenerator(entity, name, entity.posX, entity.posY, entity.posZ, color.x, color.y,
		color.z, radius, amount);
    }

}
