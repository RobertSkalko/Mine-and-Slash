package com.robertx22.mine_and_slash.data_generation.sets;

import com.robertx22.mine_and_slash.data_generation.BaseDataPackManager;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import net.minecraft.data.DataGenerator;

public class SetDataPackManager extends BaseDataPackManager<Set> {
    static String ID = "sets";

    public SetDataPackManager() {
        super(SlashRegistryType.SET, ID, x -> Set.EMPTY.fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<Set>(gen, SlashRegistry.Sets()
            .getSerializable(), ID);
    }
}
