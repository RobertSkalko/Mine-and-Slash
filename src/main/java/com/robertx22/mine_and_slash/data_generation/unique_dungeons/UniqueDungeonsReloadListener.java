package com.robertx22.mine_and_slash.data_generation.unique_dungeons;

import net.minecraft.client.resources.ReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UniqueDungeonsReloadListener extends ReloadListener<List<ResourceLocation>> {

    @Override
    protected List<ResourceLocation> prepare(IResourceManager iResourceManager, IProfiler iProfiler) {

        Collection<ResourceLocation> coll = iResourceManager.getAllResourceLocations("structures/unique_dungeon", x -> x.endsWith(".nbt"));

        return new ArrayList<>(coll);

    }

    @Override
    protected void apply(List<ResourceLocation> resourceLocations, IResourceManager iResourceManager, IProfiler iProfiler) {

    }
}
