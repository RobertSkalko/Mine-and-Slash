package com.robertx22.mine_and_slash.database.serialization.affixes;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.serialization.statmods.BaseDataPackManager;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.db_lists.registry.empty_entries.EmptyAffix;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import net.minecraft.data.DataGenerator;

public class AffixDataPackManager extends BaseDataPackManager<BaseAffix> {
    static String ID = "affixes";

    public AffixDataPackManager() {
        super(SlashRegistryType.AFFIX, ID, x -> EmptyAffix.getInstance().fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<BaseAffix>(gen, SlashRegistry.Affixes().getSerializable(), ID);
    }
}
