package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.affixes.data.WeaponPrefixes;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.List;

public class Prefixes implements IRandomDefault<BaseAffix>, ISlashRegistryInit {

    @Override
    public void registerAll() {

        new WeaponPrefixes().registerAll();

    }

    public static final Prefixes INSTANCE = new Prefixes();

    @Override
    public List<BaseAffix> All() {
        return SlashRegistry.Affixes()
            .getWrapped()
            .ofAffixType(BaseAffix.Type.prefix).list;
    }

}
