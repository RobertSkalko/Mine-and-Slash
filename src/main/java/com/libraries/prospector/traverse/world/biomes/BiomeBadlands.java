package com.libraries.prospector.traverse.world.biomes;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

import com.libraries.prospector.traverse.config.TraverseConfig;
import com.libraries.prospector.traverse.world.WorldGenConstants;
import com.libraries.prospector.traverse.world.features.WorldGenFallenTree;

public class BiomeBadlands extends Biome implements WorldGenConstants {

	protected static final WorldGenFallenTree FALLEN_TREE_FEATURE = new WorldGenFallenTree(true);
	protected static final WorldGenBlockBlob COBBLESTONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.COBBLESTONE, 1);
	protected static final WorldGenLakes LAVA_LAKE_FEATURE = new WorldGenLakes(Blocks.LAVA);

	public static BiomeProperties properties = new BiomeProperties("Badlands");

	static {
		properties.setTemperature(0.6F);
		properties.setRainfall(0.1F);
		properties.setBaseHeight(0.6F);
		properties.setHeightVariation(0.55F);
	}

	public BiomeBadlands() {
		super(properties);
		decorator.treesPerChunk = 1;
		decorator.extraTreeChance = 4;
		decorator.flowersPerChunk = 0;
		decorator.grassPerChunk = 16;

		spawnableCreatureList.clear();
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 4, 2, 4));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 3, 2, 4));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 3, 2, 4));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 2, 2, 4));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 1, 2, 7));
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0xFFDBC77C);
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		if (TraverseConfig.disableCustomSkies)
			return super.getSkyColorByTemp(currentTemperature);
		else
			return 0xFFFFCE96;
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0xFFC2C168);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		int num = rand.nextInt(2);
		if (num == 0) {
			return new WorldGenShrub(OAK_LOG, OAK_LEAVES);
		}

		return TREE_FEATURE;
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (noiseVal > 1.5D) {
			this.topBlock = Blocks.STONE.getDefaultState();
			this.fillerBlock = Blocks.STONE.getDefaultState();
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.DIRT.getDefaultState();
		}

		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE)) {
			int fallenTreeChance = rand.nextInt(3);
			if (fallenTreeChance == 0) {
				int k6 = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				FALLEN_TREE_FEATURE.generate(worldIn, rand, blockpos);
			}
		}

		if (!TraverseConfig.disallowBoulders && net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
			int boulderChance = rand.nextInt(5);
			if (boulderChance == 0) {
				int k6 = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				COBBLESTONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
			}
		}

		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA)) {
			int boulderChance = rand.nextInt(12);
			if (boulderChance == 0) {
				int k6 = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				LAVA_LAKE_FEATURE.generate(worldIn, rand, blockpos);
			}
		}

		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
			DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);
			for (int i = 0; i < 7; ++i) {
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
			}
		}

		super.decorate(worldIn, rand, pos);
	}
}
