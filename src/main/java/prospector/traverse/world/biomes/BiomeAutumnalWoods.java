package prospector.traverse.world.biomes;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.world.WorldGenConstants;

public class BiomeAutumnalWoods extends Biome implements WorldGenConstants {

	public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Autumnal Woods");

	static {
		properties.setTemperature(Biomes.FOREST.getDefaultTemperature());
		properties.setRainfall(Biomes.FOREST.getRainfall());
		properties.setBaseHeight(Biomes.FOREST.getBaseHeight());
		properties.setHeightVariation(Biomes.FOREST.getHeightVariation());
	}

	public BiomeAutumnalWoods() {
		super(properties);
		decorator.treesPerChunk = 10;
		decorator.flowersPerChunk = 4;
		decorator.grassPerChunk = 6;

		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
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
