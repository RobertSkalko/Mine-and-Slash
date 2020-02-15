package com.robertx22.mine_and_slash.onevent.data_gen;

import com.google.gson.JsonObject;

public interface IFromRegistrySerializable<T> {

    JsonObject toRegistryJson();

    T fromRegistryJson(JsonObject json);

}
