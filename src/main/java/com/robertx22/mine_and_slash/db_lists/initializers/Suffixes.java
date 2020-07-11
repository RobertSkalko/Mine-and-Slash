package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.affixes.Affix;
import com.robertx22.mine_and_slash.database.affixes.data.JewelrySuffixes;
import com.robertx22.mine_and_slash.database.affixes.data.NonWeaponSuffixes;
import com.robertx22.mine_and_slash.database.affixes.data.WeaponSuffixes;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.List;

public class Suffixes implements IRandomDefault<Affix>, ISlashRegistryInit {

    public static Suffixes INSTANCE = new Suffixes();

    @Override
    public void registerAll() {

        new WeaponSuffixes().registerAll();
        new NonWeaponSuffixes().registerAll();
        new JewelrySuffixes().registerAll();

    }

    @Override
    public List<Affix> All() {
        return SlashRegistry.Affixes()
            .getWrapped()
            .ofAffixType(Affix.Type.suffix).list;
    }

}
