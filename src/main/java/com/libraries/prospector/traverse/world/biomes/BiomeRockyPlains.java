package com.libraries.prospector.traverse.world.biomes;

import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

import com.libraries.prospector.traverse.config.TraverseConfig;
import com.libraries.prospector.traverse.world.WorldGenConstants;
import com.libraries.prospector.traverse.world.features.WorldGenPatch;

public class BiomeRockyPlains extends BiomePlains implements WorldGenConstants {

	protected static final WorldGenPatch STONE_PATCH_FEATURE = new WorldGenPatch(Blocks.STONE.getDefaultState(), 5);
	protected static final WorldGenBlockBlob COBBLESTONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.COBBLESTONE, 1);

	public static BiomeProperties properties = new BiomeProperties("Rocky Plains");

	static {
		properties.setTemperature(0.9F);
		properties.setRainfall(0.7F);
		properties.setBaseHeight(0.125F);
		properties.setHeightVariation(0.15F);
	}

	public BiomeRockyPlains() {
		super(false, properties);
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 4, 1, 5));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 2));
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		int stoneChance = rand.nextInt(4);
		if (stoneChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			STONE_PATCH_FEATURE.generate(worldIn, rand, blockpos);
		}

		if (!TraverseConfig.disallowBoulders && net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
			int genChance = rand.nextInt(4);
			if (genChance == 0) {
				int k6 = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				COBBLESTONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
			}
		}
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.TREE)) {
			int genChance = rand.nextInt(3);
			if (genChance == 0) {
				int k6 = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				OAK_SHRUB_FEATURE.generate(worldIn, rand, blockpos);
			}
		}
		super.decorate(worldIn, rand, pos);
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (noiseVal > 1.6D) {
			this.topBlock = Blocks.STONE.getDefaultState();
			this.fillerBlock = Blocks.STONE.getDefaultState();
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.DIRT.getDefaultState();
		}

		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return super.createBiomeDecorator();
	}

	public class DecoratorRockyPlains extends BiomeDecorator {
		public DecoratorRockyPlains() {
			treesPerChunk = 0;
			extraTreeChance = 0.15F;
			flowersPerChunk = 4;
			grassPerChunk = 10;
		}

		@Override
		protected void genStandardOre1(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight) {
			super.genStandardOre1(worldIn, random, blockCount, generator, minHeight, maxHeight);
		}

		@Override
		protected void genStandardOre2(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread) {
			for (int i = 0; i < blockCount; ++i)
			{
				BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
				generator.generate(worldIn, random, blockpos);
			}
		}
	}
}
