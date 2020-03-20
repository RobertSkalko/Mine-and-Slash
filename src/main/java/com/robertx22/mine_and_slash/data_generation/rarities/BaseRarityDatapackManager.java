package com.robertx22.mine_and_slash.data_generation.rarities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.RarityProvider;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.data.DataGenerator;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class BaseRarityDatapackManager<T extends Rarity> extends JsonReloadListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().create();

    String id;
    Function<JsonObject, T> serializer;
    BaseRaritiesContainer container;

    public BaseRarityDatapackManager(BaseRaritiesContainer container, String id, Function<JsonObject, T> serializer) {
        super(GSON, id);
        this.id = id;
        this.serializer = serializer;
        this.container = container;
    }

    public abstract RarityProvider getProvider(DataGenerator gen);

    @Override
    protected void apply(Map<ResourceLocation, JsonObject> mapToLoad, IResourceManager manager, IProfiler profilerIn) {

        System.out.println("Starting to register rarity datapacks on the server from datapacks");

        List<T> list = new ArrayList<>();

        mapToLoad.forEach((loc, json) -> {
            try {
                T object = serializer.apply(json);
                list.add(object);
            } catch (Exception exception) {
                LOGGER.error("Couldn't parse " + id + " {}", loc, exception);
            }

        });

        container.updateFromDatapack(list);

        if (container.getAllRarities()
            .isEmpty()) {
            throw new RuntimeException("Mine and Slash rarities are EMPTY after datapack loading!");
        } else {
            System.out.println("Rarity loading succeeded.");
        }

    }

}