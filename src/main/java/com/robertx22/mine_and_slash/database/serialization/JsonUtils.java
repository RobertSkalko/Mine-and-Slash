package com.robertx22.mine_and_slash.database.serialization;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializablePart;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static JsonArray stringListToJsonArray(List<String> list) {
        JsonArray json = new JsonArray();
        list.forEach(x -> json.add(new JsonPrimitive(x)));
        return json;
    }

    public static List<String> jsonArrayToStringList(JsonArray json) {
        List<String> list = new ArrayList<>();
        json.forEach(x -> list.add(x.getAsString()));
        return list;
    }

    public static <T extends ISerializablePart> JsonArray partListToJsonArray(List<T> list) {
        JsonArray json = new JsonArray();
        list.forEach(x -> json.add(x.toJson()));
        return json;
    }

    public static List<ISerializablePart> jsonArrayToPartList(JsonArray json, List<ISerializablePart> possibleParts) {
        List<ISerializablePart> list = new ArrayList<>();
        json.forEach(element -> {
            possibleParts.forEach(part -> {
                ISerializablePart newobj = (ISerializablePart) part.fromJson(element.getAsJsonObject());
                if (newobj != null) {
                    list.add(newobj);
                }
            });
        });
        return list;
    }

}
