package com.libraries.prospector.traverse.world.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

import com.libraries.prospector.traverse.world.WorldGenConstants;

public class BiomeAridHighland extends BiomeDesert implements WorldGenConstants {

	public static BiomeProperties properties = new BiomeProperties("Arid Highland");

	static {
		properties.setTemperature(Biomes.DESERT.getDefaultTemperature());
		properties.setRainfall(Biomes.DESERT.getRainfall());
		properties.setRainDisabled();
		properties.setBaseHeight(1.3F);
		properties.setHeightVariation(0.4F);
	}

	public BiomeAridHighland() {
		super(properties);
		this.topBlock = SAND;
		this.fillerBlock = SAND;
		decorator.generateFalls = false;
		decorator.treesPerChunk = 1;
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		if (rand.nextInt(3) == 0) {
			return TREE_FEATURE;
		}
		return ACACIA_TREE_FEATURE;
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (noiseVal > 1D) {
			this.topBlock = GRASS;
			this.fillerBlock = SAND;
		} else {
			this.topBlock = SAND;
			this.fillerBlock = SAND;
		}

		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return Biomes.DESERT.getSkyColorByTemp(currentTemperature);
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0xFFBACD78);
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0xFF80A02E);
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.TREE)) {
			if (rand.nextInt(5) == 0) {
				int k6 = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				OAK_SHRUB_FEATURE.generate(worldIn, rand, blockpos);
			}
		}

		super.decorate(worldIn, rand, pos);
	}
}
