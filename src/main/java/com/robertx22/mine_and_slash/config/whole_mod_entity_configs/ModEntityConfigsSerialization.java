package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ModEntityConfigsSerialization implements ISerializedConfig {

    public static ModEntityConfigsSerialization INSTANCE = new ModEntityConfigsSerialization();

    public String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    public String fileName() {
        return "ModEntityConfigs.txt";
    }

    @Override
    public void generateIfEmpty() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new ModEntityConfigs());
        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

    }

    @Override
    public void load() {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(this.getPath()));

            ModEntityConfigs.INSTANCE = new Gson().fromJson(reader, ModEntityConfigs.class);

            System.out.println("Mod Entity Configs added : " + ModEntityConfigs.INSTANCE.specificMobs
                    .size());
            System.out.println("All Mobs in mod entity Configs added : " + ModEntityConfigs.INSTANCE.allMobsInAMod
                    .size());

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

}