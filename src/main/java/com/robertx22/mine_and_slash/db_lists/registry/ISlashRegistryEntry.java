package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;

public interface ISlashRegistryEntry<C> extends IGUID, IWeighted, ITiered, IRarity {

    SlashRegistryType getSlashRegistryType();

    // this could be used for stuff like setdata, affixdata etc?  prototype
    default C getFromRegistry() {
        return (C) SlashRegistry.get(getSlashRegistryType(), this.GUID());
    }

    default void registerToSlashRegistry() {
        SlashRegistry.getRegistry(getSlashRegistryType()).register(this);
    }

}
