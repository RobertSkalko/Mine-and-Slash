package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.loot_crates.CommonerCrate;
import com.robertx22.mine_and_slash.database.loot_crates.CurrencyCrate;
import com.robertx22.mine_and_slash.database.loot_crates.RuneCrate;
import com.robertx22.mine_and_slash.database.loot_crates.UniqueCrate;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class LootCrates implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<LootCrate> All = new ArrayList<LootCrate>() {
            {
                {
                    add(new UniqueCrate());
                    add(new CommonerCrate());
                    add(new CurrencyCrate());
                    add(new RuneCrate());
                }
            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
