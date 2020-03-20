package com.robertx22.mine_and_slash.onevent.data_gen;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;

public interface ISerializedRegistryEntry<T> extends ISlashRegistryEntry<T>, IFromRegistrySerializable<T> {

    default void addToSerializables() {
        SlashRegistry.getRegistry(getSlashRegistryType())
            .addSerializable(this);
    }

    @Override
    default JsonObject toRegistryJson() {
        JsonObject json = new JsonObject();
        json.addProperty(ISerializable.ID, GUID());
        json.addProperty(ISerializable.REGISTRY, this.getSlashRegistryType().id);
        return json;

    }

    @Override
    default T fromRegistryJson(JsonObject json) {
        SlashRegistryType type = SlashRegistryType.getFromString(json.get(ISerializable.REGISTRY)
            .getAsString());
        String id = json.get(ISerializable.ID)
            .getAsString();
        return (T) SlashRegistry.get(type, id);

    }

}
