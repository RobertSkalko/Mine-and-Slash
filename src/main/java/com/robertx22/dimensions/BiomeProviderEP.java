package com.robertx22.dimensions;

import com.libraries.prospector.traverse.RegisterBiomes;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderEP extends BiomeProviderSingle {

	public BiomeProviderEP(World world) {
		super(RegisterBiomes.biomeCliffs);

	}

}