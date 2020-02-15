package com.robertx22.mine_and_slash.database.serialization.runewords;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.serialization.statmods.BaseDataPackManager;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import net.minecraft.data.DataGenerator;

public class RunewordDataPackManager extends BaseDataPackManager<RuneWord> {
    static String ID = "rune_words";

    public RunewordDataPackManager() {
        super(SlashRegistryType.RUNEWORD, ID, x -> RuneWord.EMPTY.fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<RuneWord>(gen, SlashRegistry.RuneWords().getSerializable(), ID);
    }
}
