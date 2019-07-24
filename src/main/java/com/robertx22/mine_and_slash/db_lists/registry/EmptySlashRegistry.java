package com.robertx22.mine_and_slash.db_lists.registry;

public interface EmptySlashRegistry extends ISlashRegistryEntry {

    @Override
    public default SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.EMPTY;
    }

    @Override
    public default String GUID() {
        return "";
    }
}
