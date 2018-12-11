package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.biome_providers.BPRedDesert;

import net.minecraft.world.biome.BiomeProvider;

public class RedDesertWP extends BaseWorldProvider {

    public RedDesertWP() {
	super(true);
    }

    private BiomeProvider biomeP = new BPRedDesert(world);

    @Override
    public BiomeProvider getBiomeProvider() {
	return biomeP;
    }

    @Override
    public String GUID() {
	return "RedDesertWP0";
    }

    @Override
    public String unlocString() {
	return "red_desert";
    }

}
