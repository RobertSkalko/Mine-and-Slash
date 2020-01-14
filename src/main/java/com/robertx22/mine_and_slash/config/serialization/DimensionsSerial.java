package com.robertx22.mine_and_slash.config.serialization;

import com.google.gson.Gson;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionsContainer;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;

public class DimensionsSerial implements ISerializedConfig<DimensionsContainer> {

    public static DimensionsSerial INSTANCE = new DimensionsSerial();

    public String fileName() {
        return "DimensionConfigs.txt";
    }

    @Override
    public DimensionsContainer getDefaultObject() {
        return new DimensionsContainer();
    }

    @Override
    public DimensionsContainer loadFromString(String string) {
        return new Gson().fromJson(string, DimensionsContainer.class);
    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return ConfigRegister.Config.DIMENSIONS;
    }

}
