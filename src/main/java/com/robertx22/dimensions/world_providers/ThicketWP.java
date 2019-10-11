package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.biome_providers.BPThicket;

import net.minecraft.world.biome.BiomeProvider;

public class ThicketWP extends BaseWorldProvider {

    public ThicketWP() {
	super(true);
    }

    private BiomeProvider biomeP = new BPThicket(world);

    @Override
    public BiomeProvider getBiomeProvider() {
	return biomeP;
    }

    @Override
    public String GUID() {
	return "ThicketWP0";
    }

    @Override
    public String unlocString() {
	return "thicket";
    }

}
