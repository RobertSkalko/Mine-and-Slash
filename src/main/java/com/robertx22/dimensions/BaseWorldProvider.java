package com.robertx22.dimensions;

import java.util.Random;
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

	public static Random ran = new Random();

	@Override
	public IChunkGenerator createChunkGenerator() {

		return new ChunkGeneratorOverworld(world, this.ran.nextLong(), true, "");

	}
}