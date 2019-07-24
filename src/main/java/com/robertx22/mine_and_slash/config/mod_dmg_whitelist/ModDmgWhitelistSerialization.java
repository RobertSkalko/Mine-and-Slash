package com.robertx22.mine_and_slash.config.mod_dmg_whitelist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ModDmgWhitelistSerialization implements ISerializedConfig {

    public static ModDmgWhitelistSerialization INSTANCE = new ModDmgWhitelistSerialization();

    public String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    public String fileName() {
        return "ModDamageWhitelist.txt";
    }

    @Override
    public void generateIfEmpty() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new ModDmgWhitelistContainer());
        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

    }

    @Override
    public void load() {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(this.getPath()));

            ModDmgWhitelistContainer.INSTANCE = new Gson().fromJson(reader, ModDmgWhitelistContainer.class);

            System.out.println("Mod Damage whitelists added : " + ModDmgWhitelistContainer.INSTANCE.modList
                    .size());
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

}