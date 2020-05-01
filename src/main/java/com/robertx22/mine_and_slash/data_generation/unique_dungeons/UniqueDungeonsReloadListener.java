package com.robertx22.mine_and_slash.data_generation.unique_dungeons;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UniqueDungeonsReloadListener extends ReloadListener<List<ResourceLocation>> {

    public static HashMap<String, List<ResourceLocation>> MAP = new HashMap<>();
    public static String ID = "unique_dungeon";

    @Override
    protected List<ResourceLocation> prepare(IResourceManager resManager, IProfiler iProfiler) {
        Collection<ResourceLocation> coll = resManager.getAllResourceLocations(
            "structures/" + ID,
            x -> x.contains(Ref.MODID) && x.endsWith(".nbt"));
        return new ArrayList<>(coll);
    }

    @Override
    protected void apply(List<ResourceLocation> list, IResourceManager iResourceManager, IProfiler iProfiler) {

        MAP.clear();

        list.forEach(x -> {

            String path = x.getPath();
            String search = ID + "/";

            int start = path.indexOf(search) + search.length();
            String cut = path.substring(start);
            int end = cut
                .indexOf("/") + path.length() - cut.length();

            String dungeonName = path.substring(start, end);

            if (!MAP.containsKey(dungeonName)) {
                MAP.put(dungeonName, new ArrayList<>());
            }

            MAP.get(dungeonName)
                .add(x);

        });

    }
}
