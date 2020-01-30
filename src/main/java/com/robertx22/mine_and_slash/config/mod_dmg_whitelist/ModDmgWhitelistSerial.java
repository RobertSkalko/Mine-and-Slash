package com.robertx22.mine_and_slash.config.mod_dmg_whitelist;

import com.google.gson.Gson;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

public class ModDmgWhitelistSerial implements ISerializedConfig<ModDmgWhitelistContainer> {

    public static ModDmgWhitelistSerial INSTANCE = new ModDmgWhitelistSerial();

    public String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    public String fileName() {
        return "ModDamageWhitelist.txt";
    }

    @Override
    public ModDmgWhitelistContainer loadFromString(String string) {
        return new Gson().fromJson(string, ModDmgWhitelistContainer.class);
    }

    @Override
    public ModDmgWhitelistContainer getDefaultObject() {
        return new ModDmgWhitelistContainer();
    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return ConfigRegister.Config.MOD_DMG_WHITELIST;
    }

}