package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;

public abstract class Suffix extends BaseAffix implements ISlashRegistryEntry<Suffix> {

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".suffix." + formattedGUID();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Suffixes;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SUFFIX;
    }

    @Override
    public int Tier() {
        return 0;
    }
}
