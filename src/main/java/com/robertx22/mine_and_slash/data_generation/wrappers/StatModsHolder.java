package com.robertx22.mine_and_slash.data_generation.wrappers;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.JsonUtils;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatModsHolder implements ISerializable<StatModsHolder> {

    public static ISerializable<StatModsHolder> SERIALIZER = new StatModsHolder();

    List<String> mods = new ArrayList<>();

    public StatModsHolder(List<StatMod> mods) {
        this.mods = mods.stream()
            .map(x -> x.GUID())
            .collect(Collectors.toList());
    }

    public StatModsHolder(List<StatMod> mods, List<StatMod> mods2) {
        mods.forEach(x -> this.mods.add(x.GUID()));
        mods2.forEach(x -> this.mods.add(x.GUID()));
    }

    public StatModsHolder(StatMod mod) {
        this.mods = Arrays.asList(mod.GUID());
    }

    public StatModsHolder(StatMod... mods) {
        for (StatMod mod : mods) {
            this.mods.add(mod.GUID());
        }
    }

    public StatModsHolder(List<StatMod> list, StatMod... mods) {
        for (StatMod mod : mods) {
            this.mods.add(mod.GUID());
        }
        list.forEach(x -> this.mods.add(x.GUID()));
    }

    private StatModsHolder() {
    }

    public List<StatMod> getMods() {
        return mods.stream()
            .map(x -> SlashRegistry.StatMods()
                .get(x))
            .collect(Collectors.toList());
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("mods", JsonUtils.stringListToJsonArray(mods));
        return json;
    }

    @Override
    public StatModsHolder fromJson(JsonObject json) {
        List<String> gmods = JsonUtils.jsonArrayToStringList(json.get("mods")
            .getAsJsonArray());
        StatModsHolder holder = new StatModsHolder();
        holder.mods = gmods;
        return holder;
    }
}
