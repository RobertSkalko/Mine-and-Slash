package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.biome_providers.BPCliffs;

import net.minecraft.world.biome.BiomeProvider;

public class CliffWP extends BaseWorldProvider {

    private BiomeProvider biomeP = new BPCliffs(world);

    public CliffWP() {
	super(true);
    }

    @Override
    public BiomeProvider getBiomeProvider() {
	return biomeP;
    }

    @Override
    public String GUID() {
	return "CliffWP0";
    }

    @Override
    public String unlocString() {
	return "cliffs";
    }

}
