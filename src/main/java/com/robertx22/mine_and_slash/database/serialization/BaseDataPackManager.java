package com.robertx22.mine_and_slash.database.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.data.DataGenerator;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.function.Function;

public abstract class BaseDataPackManager<T extends ISlashRegistryEntry> extends JsonReloadListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().create();

    String id;
    Function<JsonObject, T> serializer;
    SlashRegistryType registryType;

    public BaseDataPackManager(SlashRegistryType registryType, String id, Function<JsonObject, T> serializer) {
        super(GSON, id);
        this.id = id;
        this.serializer = serializer;
    }

    public abstract SlashDataProvider getDataPackCreator(DataGenerator gen);

    @Override
    protected void apply(Map<ResourceLocation, JsonObject> mapToLoad, IResourceManager manager, IProfiler profilerIn) {

        mapToLoad.forEach((loc, json) -> {
            try {
                T object = serializer.apply(json);
                object.registerToSlashRegistry();
            } catch (Exception exception) {
                LOGGER.error("Couldn't parse " + id + " {}", loc, exception);
            }

        });

    }

}