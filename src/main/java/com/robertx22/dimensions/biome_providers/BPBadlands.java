package com.robertx22.dimensions.biome_providers;

import com.libraries.prospector.traverse.RegisterBiomes;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BPBadlands extends BiomeProviderSingle {

	public BPBadlands(World world) {
		super(RegisterBiomes.BADLANDS);

	}

}