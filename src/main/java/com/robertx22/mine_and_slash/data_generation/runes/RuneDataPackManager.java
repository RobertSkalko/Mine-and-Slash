package com.robertx22.mine_and_slash.data_generation.runes;

import com.robertx22.mine_and_slash.data_generation.BaseDataPackManager;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyRune;
import net.minecraft.data.DataGenerator;

public class RuneDataPackManager extends BaseDataPackManager<BaseRune> {
    static String ID = "runes";

    public RuneDataPackManager() {
        super(SlashRegistryType.RUNE, ID, x -> EmptyRune.getInstance()
            .fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<BaseRune>(gen, SlashRegistry.Runes()
            .getSerializable(), ID);
    }
}
