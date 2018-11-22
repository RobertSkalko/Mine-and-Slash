package com.robertx22.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;

public class BaseWorldProvider extends WorldProvider {

	public BaseWorldProvider() {

	}

	private BiomeProvider biomeP = new BiomeProviderEP(world);

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.OVERWORLD;
	}

	/**
	 * Do not override this.
	 * 
	 * Returns true on clients (to allow rendering of sky etc, maybe even clouds).
	 * Returns false on servers (to disable Nether Portal mob spawning and sleeping
	 * in beds).
	 */
	@Override
	public boolean isSurfaceWorld() {
		return (this.world == null) ? false : this.world.isRemote;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public String getSaveFolder() {
		return "Map_World" + world.provider.getDimension();
	}

	@Override
	public BiomeProvider getBiomeProvider() {
		return biomeP;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorOverworld(world, world.rand.nextLong(), true, "");

	}
}