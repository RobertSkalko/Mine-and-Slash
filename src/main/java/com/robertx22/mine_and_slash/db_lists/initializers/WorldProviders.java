package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.database.world_providers.DungeonIWP;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class WorldProviders implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseWorldProvider> All = new ArrayList<BaseWorldProvider>() {
            {
                {
                    add(new DungeonIWP(null, null));
                }

            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
