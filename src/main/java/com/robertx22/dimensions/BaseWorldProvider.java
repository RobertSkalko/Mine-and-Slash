package com.robertx22.dimensions;

import java.util.UUID;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;

public class BaseWorldProvider extends WorldProviderSurface {

	String worldname;

	BiomeProvider biome_provider;

	public BaseWorldProvider() {
		this.worldname = UUID.randomUUID().toString();

		biome_provider = new BiomeProviderEP(world);
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.OVERWORLD;
	}

	@Override
	public String getSaveFolder() {
		return worldname;
	}

	@Override
	public BiomeProvider getBiomeProvider() {
		return biome_provider;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {

		return new ChunkGeneratorOverworld(world, world.rand.nextLong(), true, "");

	}
}