package com.robertx22.dimensions;

import java.util.UUID;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;

public class BaseWorldProvider extends WorldProvider {

	String worldname;

	public BaseWorldProvider() {
		this.worldname = UUID.randomUUID().toString();
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
	public IChunkGenerator createChunkGenerator() {

		return new ChunkGeneratorOverworld(world, world.rand.nextLong(), true, "");

	}
}