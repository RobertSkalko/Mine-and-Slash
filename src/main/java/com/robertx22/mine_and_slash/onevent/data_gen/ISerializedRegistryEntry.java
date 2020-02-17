package com.robertx22.mine_and_slash.onevent.data_gen;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

public interface ISerializedRegistryEntry<T> extends ISlashRegistryEntry<T>, IFromRegistrySerializable<T> {

    default void addToSerializables() {
        SlashRegistry.getRegistry(getSlashRegistryType())
            .addSerializable(this);
    }

    static String ID = "id";
    static String REGISTRY = "registry";
    static String LANG_NAME = "lang_file_string";
    static String LANG_DESC = "lang_file_desc_string";
    static String WEIGHT = "weight";
    static String RARITY = "rarity";
    static String TIER = "tier";

    default String datapackFolder() {
        return "";
    }

    default String getGUIDFromJson(JsonObject json) {
        return json.get(ID)
            .getAsString();
    }

    default int getTierFromJson(JsonObject json) {
        return json.get(TIER)
            .getAsInt();
    }

    default int getWeightFromJson(JsonObject json) {
        return json.get(WEIGHT)
            .getAsInt();
    }

    default int getRarityFromJson(JsonObject json) {
        return json.get(RARITY)
            .getAsInt();
    }

    default String getLangDescStringFromJson(JsonObject json) {
        return json.get(LANG_DESC)
            .getAsString();
    }

    default String getLangNameStringFromJson(JsonObject json) {
        return json.get(LANG_NAME)
            .getAsString();
    }

    default JsonObject getDefaultJson() {
        JsonObject json = new JsonObject();

        json.addProperty(ID, GUID());
        json.addProperty(WEIGHT, Weight());
        json.addProperty(RARITY, getRarityRank());
        json.addProperty(TIER, Tier());
        json.addProperty(REGISTRY, this.getSlashRegistryType().id);

        if (this instanceof IAutoLocName) {
            IAutoLocName loc = (IAutoLocName) this;
            json.addProperty(LANG_NAME, loc.formattedLocNameLangFileGUID());
        }
        if (this instanceof IAutoLocDesc) {
            IAutoLocDesc loc = (IAutoLocDesc) this;
            json.addProperty(LANG_DESC, loc.formattedLocDescLangFileGUID());
        }

        return json;
    }

    @Override
    default JsonObject toRegistryJson() {
        JsonObject json = new JsonObject();
        json.addProperty(ID, GUID());
        json.addProperty(REGISTRY, this.getSlashRegistryType().id);
        return json;

    }

    @Override
    default T fromRegistryJson(JsonObject json) {
        SlashRegistryType type = SlashRegistryType.getFromString(json.get(REGISTRY)
            .getAsString());
        String id = json.get(ID)
            .getAsString();
        return (T) SlashRegistry.get(type, id);

    }

}
