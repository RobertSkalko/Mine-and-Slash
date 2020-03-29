package com.robertx22.mine_and_slash.data_generation.tiers;

import com.robertx22.mine_and_slash.data_generation.BaseDataPackManager;
import com.robertx22.mine_and_slash.database.tiers.base.Tier;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import net.minecraft.data.DataGenerator;

public class TierDatapackManager extends BaseDataPackManager<Tier> {
    static String ID = "tier";

    public TierDatapackManager() {
        super(SlashRegistryType.TIER, ID, x -> Tier.SERIALIZER.fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<Tier>(gen, SlashRegistry.Tiers()
            .getSerializable(), ID);
    }
}

