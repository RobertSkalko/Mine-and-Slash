package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.world_providers.BaseDungeonDimension;
import com.robertx22.mine_and_slash.database.world_providers.DungeonDimension;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class WorldProviders implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseDungeonDimension> All = new ArrayList<BaseDungeonDimension>() {
            {
                {
                    add(new DungeonDimension(null, null));
                }

            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
