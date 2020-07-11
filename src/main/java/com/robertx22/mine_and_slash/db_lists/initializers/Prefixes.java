package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.affixes.Affix;
import com.robertx22.mine_and_slash.database.affixes.data.ArmorPrefixes;
import com.robertx22.mine_and_slash.database.affixes.data.JewelryPrefixes;
import com.robertx22.mine_and_slash.database.affixes.data.WeaponPrefixes;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.List;

public class Prefixes implements IRandomDefault<Affix>, ISlashRegistryInit {

    @Override
    public void registerAll() {

        new WeaponPrefixes().registerAll();
        new ArmorPrefixes().registerAll();
        new JewelryPrefixes().registerAll();

    }

    public static final Prefixes INSTANCE = new Prefixes();

    @Override
    public List<Affix> All() {
        return SlashRegistry.Affixes()
            .getWrapped()
            .ofAffixType(Affix.Type.prefix).list;
    }

}
