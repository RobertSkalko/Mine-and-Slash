package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.loot_crates.LootCrate;
import com.robertx22.mine_and_slash.database.loot_crates.UniqueCrate;
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

                }
            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
