package com.robertx22.mine_and_slash.onevent.data_gen;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.IGUID;

public interface ISerializable<T> extends IGUID {
    JsonObject toJson();

    T fromJson(JsonObject json);

}
