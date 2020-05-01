package com.robertx22.mine_and_slash.data_generation.unique_dungeons;

import com.robertx22.mine_and_slash.data_generation.BaseDataPackManager;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import net.minecraft.data.DataGenerator;

public class UniqueDungeonDatapackManager extends BaseDataPackManager<UniqueDungeon> {
    static String ID = "unique_dungeon";

    public UniqueDungeonDatapackManager() {
        super(SlashRegistryType.UNIQUE_DUNGEON, ID, x -> UniqueDungeon.EMPTY
            .fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<UniqueDungeon>(gen, SlashRegistry.UniqueDungeons()
            .getSerializable(), ID);
    }
}
