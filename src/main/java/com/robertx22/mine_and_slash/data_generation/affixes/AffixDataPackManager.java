package com.robertx22.mine_and_slash.data_generation.affixes;

import com.robertx22.mine_and_slash.data_generation.BaseDataPackManager;
import com.robertx22.mine_and_slash.database.affixes.Affix;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyAffix;
import net.minecraft.data.DataGenerator;

public class AffixDataPackManager extends BaseDataPackManager<Affix> {
    static String ID = "affixes";

    public AffixDataPackManager() {
        super(SlashRegistryType.AFFIX, ID, x -> EmptyAffix.getInstance()
            .fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new SlashDataProvider<Affix>(gen, SlashRegistry.Affixes()
            .getSerializable(), ID);
    }
}
