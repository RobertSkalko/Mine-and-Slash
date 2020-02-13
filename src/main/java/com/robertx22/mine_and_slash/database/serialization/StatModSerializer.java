package com.robertx22.mine_and_slash.database.serialization;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class StatModSerializer implements ISerializable<StatMod> {

    private StatModSerializer() {
    }

    public static StatModSerializer getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public JsonObject toJson(StatMod mod) {

        JsonObject json = new JsonObject();

        json.add("min", new JsonPrimitive(mod.Min()));
        json.add("max", new JsonPrimitive(mod.Max()));
        json.add("multi", new JsonPrimitive(mod.multiplier));
        json.add("stat", new JsonPrimitive(mod.GetBaseStat().GUID()));
        json.add("type", new JsonPrimitive(mod.Type().name()));

        return json;
    }

    @Override
    public StatMod fromJson(JsonObject json) {

        float min = json.get("min").getAsFloat();
        float max = json.get("max").getAsFloat();
        float multi = json.get("multi").getAsFloat();
        String stat = json.get("stat").getAsString();
        StatTypes type = StatTypes.valueOf(json.get("type").getAsString());

        return new SerializableStatMod(stat, min, max, type, multi);
    }

    private static class SingletonHolder {
        private static final StatModSerializer INSTANCE = new StatModSerializer();
    }
}
