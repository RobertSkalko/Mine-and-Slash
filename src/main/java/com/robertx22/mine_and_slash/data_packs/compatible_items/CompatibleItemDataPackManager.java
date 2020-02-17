package com.robertx22.mine_and_slash.data_packs.compatible_items;

import com.robertx22.mine_and_slash.data_packs.BaseDataPackManager;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import net.minecraft.data.DataGenerator;

public class CompatibleItemDataPackManager extends BaseDataPackManager<CompatibleItem> {
    static String ID = "compatible_items";

    public CompatibleItemDataPackManager() {
        super(SlashRegistryType.COMPATIBLE_ITEM, ID, x -> CompatibleItem.EMPTY.fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<CompatibleItem>(gen, SlashRegistry.CompatibleItems()
            .getSerializable(), ID);
    }
}
