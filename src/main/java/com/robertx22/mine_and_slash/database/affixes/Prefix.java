package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;

public abstract class Prefix extends BaseAffix implements ISlashRegistryEntry<Prefix> {

    public Prefix() {

    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".prefix." + formattedGUID();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Prefixes;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.PREFIX;
    }

    @Override
    public int Tier() {
        return 0;
    }
}
