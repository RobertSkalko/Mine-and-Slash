package com.robertx22.uncommon.oregen;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGen implements IWorldGenerator {

	WorldGenerator oreGen;
	int minHeight;
	int maxHeight;
	int spawnChance;
	/**
	 * Nether: -1 Overworld: 0 The End: 1
	 */
	int dimension;

	public OreGen(Block blockToGen, int blockCount, int minHeight, int maxHeight, int spawnChance) {
		this(blockToGen, blockCount, minHeight, maxHeight, spawnChance, 0, BlockMatcher.forBlock(Blocks.STONE));
	}

	public OreGen(Block blockToGen, int blockCount, int minHeight, int maxHeight, int spawnChance, int dimension) {
		this(blockToGen, blockCount, minHeight, maxHeight, spawnChance, dimension, BlockMatcher.forBlock(Blocks.STONE));
	}

	public OreGen(Block blockToGen, int blockAmount, int minHeight, int maxHeight, int spawnChance, int dimension,
			Predicate<IBlockState> blockToReplace) {
		oreGen = new WorldGenMinable(blockToGen.getDefaultState(), blockAmount, blockToReplace);
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.spawnChance = spawnChance;
		this.dimension = dimension;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == dimension) {
			this.runGenerator(this.oreGen, world, random, chunkX, chunkZ, spawnChance, minHeight, maxHeight);
		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z,
			int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		}

		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightdiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}
}