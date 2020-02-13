package com.robertx22.mine_and_slash.database.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Map;

public class StatModManager extends JsonReloadListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().create();

    //private Map<ResourceLocation, StatMod> map = ImmutableMap.of();

    public StatModManager() {
        super(GSON, "stat_mods");
    }

    @Override
    protected Map<ResourceLocation, JsonObject> prepare(IResourceManager manager, IProfiler profilerIn) {

        Map<ResourceLocation, JsonObject> map = super.prepare(manager, profilerIn);

        map.keySet().forEach(x -> System.out.println(x.toString()));

        return map;

    }

    public final void registerAll() {

        Collection<ResourceLocation> all = MapManager.getServer()
                .getResourceManager()
                .getAllResourceLocations("stat_mods", x -> x.endsWith(".json"));

        int a;

    }

    @Override
    protected void apply(Map<ResourceLocation, JsonObject> mapToLoad, IResourceManager manager, IProfiler profilerIn) {

        //SlashRegistry.StatMods().clear;

        mapToLoad.forEach((loc, json) -> {
            try {
                StatMod mod = StatModSerializer.getInstance().fromJson(json);

                mod.registerToSlashRegistry();
            } catch (Exception exception) {
                LOGGER.error("Couldn't parse StatMod {}", loc, exception);
            }

        });

    }

}