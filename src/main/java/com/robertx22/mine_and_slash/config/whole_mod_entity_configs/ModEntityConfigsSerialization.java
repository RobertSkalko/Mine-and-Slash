package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ModEntityConfigsSerialization implements ISerializedConfig {

    public static ModEntityConfigsSerialization INSTANCE = new ModEntityConfigsSerialization();

    public String fileName() {
        return "ModEntityConfigs.txt";
    }

    @Override
    public void load() {

        try {
            JsonReader reader = new JsonReader(new FileReader(this.getPath()));

            ModEntityConfigs all = new Gson().fromJson(reader, ModEntityConfigs.class);

            all.registerAll();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getDefaultObject() {
        return new ModEntityConfigs();
    }

}