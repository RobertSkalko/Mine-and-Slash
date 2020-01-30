package com.robertx22.mine_and_slash.config.base_player_stat;

import com.google.gson.Gson;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;

public class BasePlayerStatSerial implements ISerializedConfig<BasePlayerStatContainer> {
    public static BasePlayerStatSerial INSTANCE = new BasePlayerStatSerial();

    @Override
    public String fileName() {
        return "BasePlayerStats.txt";
    }

    @Override
    public BasePlayerStatContainer getDefaultObject() {
        return BasePlayerStatContainer.defaultStats();
    }

    @Override
    public BasePlayerStatContainer loadFromString(String string) {
        return new Gson().fromJson(string, BasePlayerStatContainer.class);
    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return ConfigRegister.Config.BASE_PLAYER_STATS;
    }
}
