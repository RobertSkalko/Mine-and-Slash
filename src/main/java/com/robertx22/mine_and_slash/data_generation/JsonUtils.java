package com.robertx22.mine_and_slash.data_generation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializablePart;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtils {

    public static void addStats(List<StatModifier> mods, JsonObject json, String id) {
        JsonArray array = new JsonArray();
        mods.stream()
            .map(x -> x.toJson())
            .collect(Collectors.toList())
            .forEach(x -> array.add(x));
        json.add(id, array);
    }

    public static List<StatModifier> getStats(JsonObject json, String id) {
        List<StatModifier> mods = new ArrayList<>();
        json.getAsJsonArray(id)
            .forEach(x -> mods.add(StatModifier.EMPTY.fromJson(x.getAsJsonObject())));
        return mods;
    }

    public static void addExactStats(List<ExactStatData> list, JsonObject json, String id) {
        JsonArray array = new JsonArray();
        list.stream()
            .map(x -> x.toJson())
            .collect(Collectors.toList())
            .forEach(x -> array.add(x));
        json.add(id, array);
    }

    public static List<ExactStatData> getExactStats(JsonObject json, String id) {
        List<ExactStatData> list = new ArrayList<>();
        json.getAsJsonArray(id)
            .forEach(x -> list.add(ExactStatData.EMPTY.fromJson(x.getAsJsonObject())));
        return list;
    }

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
            if (element != null && element.getAsJsonObject() != null) {
                possibleParts.forEach(part -> {
                    Object newobj = null;

                    try {
                        newobj = part.fromJson(element.getAsJsonObject());
                    } catch (Exception e) {

                    }

                    if (newobj instanceof ISerializablePart) {
                        list.add((ISerializablePart) newobj);
                    }
                });
            }
        });
        return list;
    }

}
