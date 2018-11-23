package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.biome_providers.BiomeProviderCliffs;

import net.minecraft.world.biome.BiomeProvider;

public class CliffWP extends BaseWorldProvider {

	private BiomeProvider biomeP = new BiomeProviderCliffs(world);

	@Override
	public BiomeProvider getBiomeProvider() {
		return biomeP;
	}

	@Override
	public String GUID() {
		return "CliffWP0";
	}

}
