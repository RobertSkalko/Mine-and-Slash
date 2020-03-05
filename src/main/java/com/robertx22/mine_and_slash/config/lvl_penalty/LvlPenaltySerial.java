package com.robertx22.mine_and_slash.config.lvl_penalty;

import com.google.gson.Gson;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

public class LvlPenaltySerial implements ISerializedConfig<LvlPenaltyContainer> {

    public static LvlPenaltySerial INSTANCE = new LvlPenaltySerial();

    public String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    public String fileName() {
        return "Level_Penalties.txt";
    }

    @Override
    public LvlPenaltyContainer loadFromString(String string) {
        return new Gson().fromJson(string, LvlPenaltyContainer.class);
    }

    @Override
    public boolean isSyncedToClient() {
        return false;
    }

    @Override
    public LvlPenaltyContainer getDefaultObject() {
        return LvlPenaltyContainer.defaultPenalty();
    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return ConfigRegister.Config.LVL_PENALTY;
    }

}
