package com.robertx22.mine_and_slash.config.dimension_configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigDimensionsSerialization implements ISerializedConfig {

    public static ConfigDimensionsSerialization INSTANCE = new ConfigDimensionsSerialization();

    public String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    public String fileName() {
        return "DimensionConfigs.txt";
    }

    public void generateIfEmpty() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new DimensionsContainer());

        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

    }

    @Override
    public void load() {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(this.getPath()));

            DimensionsContainer.INSTANCE = new Gson().fromJson(reader, DimensionsContainer.class);

            System.out.println("Dimensions added to config: " + DimensionsContainer.INSTANCE.dimensionsList
                    .size());
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

}
