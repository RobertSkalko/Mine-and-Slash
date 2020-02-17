package com.robertx22.mine_and_slash.config.dimension_configs;

import com.robertx22.mine_and_slash.config.base.IConfig;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;

import java.util.HashMap;
import java.util.Map;

public class DimensionsContainer implements IConfig, ISlashRegistryInit {

    public DimensionsContainer() {

        if (dimensionsList.isEmpty()) {
            dimensionsList.put("minecraft:overworld", (DimensionConfig.Overworld()));
            dimensionsList.put("minecraft:the_end", (DimensionConfig.End()));
            dimensionsList.put("minecraft:the_nether", (DimensionConfig.Nether()));
        }
    }

    String version = "1.0";

    public String ConfigType = "dimensions_config";

    @Override
    public String GUID() {
        return ConfigType;
    }

    DimensionConfig defaultconfig = DimensionConfig.DefaultExtra();

    public HashMap<String, DimensionConfig> dimensionsList = new HashMap();

    @Override
    public void registerAll() {
        for (Map.Entry<String, DimensionConfig> entry : this.dimensionsList.entrySet()) {
            entry.getValue().GUID = entry.getKey();
            entry.getValue().registerToSlashRegistry();
        }

        SlashRegistry.getRegistry(SlashRegistryType.DIMENSION_CONFIGS).setDefault(this.defaultconfig);

    }
}
