package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import com.google.gson.Gson;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;

public class ModEntityConfigsSerial implements ISerializedConfig<ModEntityConfigs> {

    public static ModEntityConfigsSerial INSTANCE = new ModEntityConfigsSerial();

    public String fileName() {
        return "ModEntityConfigs.txt";
    }

    @Override
    public ModEntityConfigs getDefaultObject() {
        return new ModEntityConfigs();
    }

    @Override
    public ModEntityConfigs loadFromString(String string) {
        return new Gson().fromJson(string, ModEntityConfigs.class);
    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return ConfigRegister.Config.MOD_ENTITY;
    }

}