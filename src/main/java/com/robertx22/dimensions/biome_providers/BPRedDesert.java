package com.robertx22.dimensions.biome_providers;

import com.libraries.prospector.traverse.RegisterBiomes;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BPRedDesert extends BiomeProviderSingle {

	public BPRedDesert(World world) {
		super(RegisterBiomes.RED_DESERT);

	}

}