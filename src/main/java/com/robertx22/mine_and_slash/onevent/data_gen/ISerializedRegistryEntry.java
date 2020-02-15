package com.robertx22.mine_and_slash.onevent.data_gen;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

public interface ISerializedRegistryEntry<T> extends ISlashRegistryEntry<T> {

    default void addToSerializables() {
        SlashRegistry.getRegistry(getSlashRegistryType()).addSerializable(this);
    }

}
