package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.DimensionConfig;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class DimConfigs implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        DimensionConfig.Overworld()
            .addToSerializables();
        DimensionConfig.Nether()
            .addToSerializables();
        DimensionConfig.End()
            .addToSerializables();
    }
}
