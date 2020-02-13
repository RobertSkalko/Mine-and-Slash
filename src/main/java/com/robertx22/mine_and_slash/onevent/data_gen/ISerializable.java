package com.robertx22.mine_and_slash.onevent.data_gen;

import com.google.gson.JsonObject;

public interface ISerializable<T> {
    JsonObject toJson(T obj);

    T fromJson(JsonObject json);

}
