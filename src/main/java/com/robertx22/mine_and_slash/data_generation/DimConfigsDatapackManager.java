package com.robertx22.mine_and_slash.data_generation;

import com.robertx22.mine_and_slash.database.DimensionConfig;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import net.minecraft.data.DataGenerator;

public class DimConfigsDatapackManager extends BaseDataPackManager<DimensionConfig> {
    static String ID = "dimension_config";

    public DimConfigsDatapackManager() {
        super(SlashRegistryType.DIMENSION_CONFIGS, ID, x -> DimensionConfig.EMPTY
            .fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<DimensionConfig>(gen, SlashRegistry.DimensionConfigs()
            .getSerializable(), ID);
    }
}
