package com.robertx22.mine_and_slash.config.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

public interface ISerializedConfig {

    public abstract String fileName();

    public abstract void load();

    public default String getPath() {
        return folder() + "/" + fileName();
    }

    Object getDefaultObject();

    default String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    default void generateIfEmpty() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(getDefaultObject());
        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);
    }

}
