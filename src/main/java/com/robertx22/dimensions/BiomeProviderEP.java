package com.robertx22.dimensions;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProviderSingle;
import prospector.traverse.RegisterBiomes;

public class BiomeProviderEP extends BiomeProviderSingle {

	public BiomeProviderEP(World world) {
		super(RegisterBiomes.biomeCliffs);

	}

}