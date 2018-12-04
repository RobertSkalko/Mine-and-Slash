package com.libraries.prospector.traverse.world.biomes;

import java.util.Random;

import com.libraries.prospector.traverse.world.WorldGenConstants;
import com.libraries.prospector.traverse.world.features.WorldGenPatch;
import com.libraries.prospector.traverse.world.features.WorldGenSurfacePatch;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class BiomeThicket extends Biome implements WorldGenConstants {

    protected static final WorldGenSurfacePatch PODZOL_PATCH_FEATURE = new WorldGenSurfacePatch(
	    Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 6);
    protected static final WorldGenPatch GRAVEL_PATCH_FEATURE = new WorldGenPatch(Blocks.GRAVEL.getDefaultState(), 4);

    public static BiomeProperties properties = new BiomeProperties("Thicket");

    static {
	properties.setTemperature(0.8F);
	properties.setRainfall(0.3F);
	properties.setBaseHeight(0.2F);
	properties.setHeightVariation(0.3F);
    }

    public BiomeThicket() {
	super(properties);
	decorator.treesPerChunk = 33;
	decorator.extraTreeChance = 4;
	decorator.flowersPerChunk = 0;
	decorator.grassPerChunk = 3;

	spawnableCreatureList.clear();
	spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 4, 2, 4));
	spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 3, 2, 4));
	spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 3, 2, 4));
	spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 2, 2, 4));
	spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 1, 2, 7));
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
	return super.getModdedBiomeGrassColor(0xFF74CD61);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
	return super.getModdedBiomeFoliageColor(0xFF6DAF61);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
	return TALLER_OAK_TREE_FEATURE;
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {

	if (noiseVal > 1.5D) {
	    this.topBlock = Blocks.GRASS.getDefaultState();
	    this.fillerBlock = Blocks.DIRT.getDefaultState();
	} else {
	    this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT,
		    BlockDirt.DirtType.COARSE_DIRT);
	    this.fillerBlock = Blocks.DIRT.getDefaultState();
	}

	this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
	int podzolChance = rand.nextInt(4);
	if (podzolChance == 0) {
	    int k6 = rand.nextInt(16) + 8;
	    int l = rand.nextInt(16) + 8;
	    BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	    PODZOL_PATCH_FEATURE.generate(worldIn, rand, blockpos);
	}

	int gravelChance = rand.nextInt(5);
	if (gravelChance == 0) {
	    int k6 = rand.nextInt(16) + 8;
	    int l = rand.nextInt(16) + 8;
	    BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	    GRAVEL_PATCH_FEATURE.generate(worldIn, rand, blockpos);
	}

	if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos,
		DecorateBiomeEvent.Decorate.EventType.TREE)) {
	    int fallenTreeChance = rand.nextInt(2);
	    if (fallenTreeChance == 0) {
		int k6 = rand.nextInt(16) + 8;
		int l = rand.nextInt(16) + 8;
		BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
		OAK_FALLEN_TREE_FEATURE.generate(worldIn, rand, blockpos);
	    }
	}

	if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos,
		DecorateBiomeEvent.Decorate.EventType.GRASS)) {
	    DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);
	    for (int i = 0; i < 5; ++i) {
		int j = rand.nextInt(16) + 8;
		int k = rand.nextInt(16) + 8;
		int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
		DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	    }
	}

	if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos,
		DecorateBiomeEvent.Decorate.EventType.TREE)) {
	    int k6 = rand.nextInt(16) + 8;
	    int l = rand.nextInt(16) + 8;
	    BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	    OAK_SHRUB_FEATURE.generate(worldIn, rand, blockpos);
	}

	super.decorate(worldIn, rand, pos);
    }
}
