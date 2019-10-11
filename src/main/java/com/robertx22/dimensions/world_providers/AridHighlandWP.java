package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.biome_providers.BPAridHighland;

import net.minecraft.world.biome.BiomeProvider;

public class AridHighlandWP extends BaseWorldProvider {

    public AridHighlandWP() {
	super(true);
    }

    private BiomeProvider biomeP = new BPAridHighland(world);

    @Override
    public BiomeProvider getBiomeProvider() {
	return biomeP;
    }

    @Override
    public String GUID() {
	return "AridHighlandWP0";
    }

    @Override
    public String unlocString() {
	return "arid_highland";
    }

}
