package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.biome_providers.BPBadlands;

import net.minecraft.world.biome.BiomeProvider;

public class BadlandsWP extends BaseWorldProvider {

    public BadlandsWP() {
	super(true);
    }

    private BiomeProvider biomeP = new BPBadlands(world);

    @Override
    public BiomeProvider getBiomeProvider() {
	return biomeP;
    }

    @Override
    public String GUID() {
	return "BadlandsWP0";
    }

    @Override
    public String unlocString() {
	return "badlands";
    }

}
