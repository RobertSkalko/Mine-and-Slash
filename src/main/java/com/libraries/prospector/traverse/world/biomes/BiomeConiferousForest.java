package com.libraries.prospector.traverse.world.biomes;

import com.libraries.prospector.traverse.world.WorldGenConstants;

import net.minecraft.world.biome.Biome;

public class BiomeConiferousForest extends Biome implements WorldGenConstants {

	public final boolean isSnowy;

	public BiomeConiferousForest(boolean isSnowy) {
		super(getProperties(isSnowy));
		this.isSnowy = isSnowy;
		decorator.treesPerChunk = 7;
		decorator.flowersPerChunk = 0;
		decorator.grassPerChunk = 16;
		decorator.mushroomsPerChunk = 2;

		spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
	}

	public static BiomeProperties getProperties(boolean isSnowy) {
		BiomeProperties properties;
		if (isSnowy) {
			properties = new BiomeProperties("Snowy Coniferous Forest");
			properties.setTemperature(0.0F);
			properties.setRainfall(0.5F);
			properties.setSnowEnabled();
		} else {
			properties = new BiomeProperties("Temperate Rainforest");
			properties.setTemperature(0.6F);
			properties.setRainfall(0.9F);
		}
		properties.setBaseHeight(0.4F);
		properties.setHeightVariation(1.1F);
		return properties;
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0xFF338235);
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0xFF338235);
	}
}
