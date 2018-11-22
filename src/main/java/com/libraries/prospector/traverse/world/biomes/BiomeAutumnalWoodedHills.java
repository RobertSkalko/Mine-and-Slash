package com.libraries.prospector.traverse.world.biomes;

import java.util.Random;

import com.libraries.prospector.traverse.config.TraverseConfig;
import com.libraries.prospector.traverse.world.WorldGenConstants;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeAutumnalWoodedHills extends Biome implements WorldGenConstants {

	public static BiomeProperties properties = new BiomeProperties("Autumnal Wooded Hills");

	static {
		properties.setTemperature(Biomes.FOREST.getDefaultTemperature());
		properties.setRainfall(Biomes.FOREST.getRainfall());
		properties.setBaseHeight(Biomes.EXTREME_HILLS.getBaseHeight());
		properties.setHeightVariation(Biomes.EXTREME_HILLS.getHeightVariation());
	}

	public BiomeAutumnalWoodedHills() {
		super(properties);
		decorator.treesPerChunk = 10;
		decorator.flowersPerChunk = 4;
		decorator.grassPerChunk = 6;

		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0xFFD6C23D);
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		if (TraverseConfig.disableCustomSkies)
			return super.getSkyColorByTemp(currentTemperature);
		else
			return 0xFFEFECD9;
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0xFFD2D31F);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		return TREE_FEATURE;
	}

}
