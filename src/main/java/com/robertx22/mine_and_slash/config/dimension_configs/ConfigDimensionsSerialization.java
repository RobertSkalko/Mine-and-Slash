package com.robertx22.mine_and_slash.config.dimension_configs;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigDimensionsSerialization implements ISerializedConfig {

    public static ConfigDimensionsSerialization INSTANCE = new ConfigDimensionsSerialization();

    public String fileName() {
        return "DimensionConfigs.txt";
    }

    @Override
    public void load() {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(this.getPath()));

            DimensionsContainer dims = new Gson().fromJson(reader, DimensionsContainer.class);
            dims.registerAll();

            System.out.println("Dimensions added to config: " + dims.dimensionsList.size());

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    @Override
    public Object getDefaultObject() {
        return new DimensionsContainer();
    }

}
