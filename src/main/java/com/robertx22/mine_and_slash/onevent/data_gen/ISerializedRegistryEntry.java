package com.robertx22.mine_and_slash.onevent.data_gen;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

public interface ISerializedRegistryEntry<T> extends ISlashRegistryEntry<T>, IFromRegistrySerializable<T> {

    default void addToSerializables() {
        SlashRegistry.getRegistry(getSlashRegistryType()).addSerializable(this);
    }

    static String ID = "id";
    static String REGISTRY = "registry";
    static String LANG_NAME = "lang_file_string";
    static String WEIGHT = "weight";
    static String RARITY = "rarity";

    default String datapackFolder() {
        return "";
    }

    default String getGUIDFromJson(JsonObject json) {
        return json.get(ID).getAsString();
    }

    default int getWeightFromJson(JsonObject json) {
        return json.get(WEIGHT).getAsInt();
    }

    default int getRarityFromJson(JsonObject json) {
        return json.get(RARITY).getAsInt();
    }

    default String getLangNameStringFromJson(JsonObject json) {
        return json.get(LANG_NAME).getAsString();
    }

    default JsonObject getDefaultJson() {
        JsonObject json = new JsonObject();

        json.addProperty(ID, GUID());
        json.addProperty(WEIGHT, Weight());
        json.addProperty(RARITY, getRarityRank());
        json.addProperty(REGISTRY, this.getSlashRegistryType().id);

        if (this instanceof IAutoLocName) {
            IAutoLocName loc = (IAutoLocName) this;
            json.addProperty(LANG_NAME, loc.formattedLocNameLangFileGUID());
        }

        return json;
    }

    @Override
    default JsonObject toRegistryJson() {
        return getDefaultJson();

    }

    @Override
    default T fromRegistryJson(JsonObject json) {
        SlashRegistryType type = SlashRegistryType.getFromString(json.get(REGISTRY).getAsString());
        String id = json.get(ID).getAsString();

        return (T) SlashRegistry.get(type, id);

    }

}
