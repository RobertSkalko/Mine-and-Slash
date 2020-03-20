package com.robertx22.mine_and_slash.database;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class MinMax implements ISerializable<MinMax> {

    public static ISerializable<MinMax> getSerializer() {
        return new MinMax(0, 0);
    }

    public int min;
    public int max;

    public MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(int num) {

        if (num >= min) {
            if (num <= max) {
                return true;
            }
        }
        return false;

    }

    public int random() {
        return RandomUtils.RandomRange(min, max);
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("min", min);
        json.addProperty("max", max);

        return json;
    }

    @Override
    public MinMax fromJson(JsonObject json) {
        return new MinMax(json.get("min")
            .getAsInt(), json.get("max")
            .getAsInt());
    }
}
