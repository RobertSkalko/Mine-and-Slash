package com.robertx22.mine_and_slash.onevent.data_gen;

import com.robertx22.mine_and_slash.data_packs.affixes.AffixDataPackManager;
import com.robertx22.mine_and_slash.data_packs.compatible_items.CompatibleItemDataPackManager;
import com.robertx22.mine_and_slash.data_packs.runes.RuneDataPackManager;
import com.robertx22.mine_and_slash.data_packs.runewords.RunewordDataPackManager;
import com.robertx22.mine_and_slash.data_packs.sets.SetDataPackManager;
import com.robertx22.mine_and_slash.data_packs.unique_gears.UniqueGearDatapackManager;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.MyAdvProvider;
import com.robertx22.mine_and_slash.uncommon.develeper.ItemModelManager;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class OnGatherData {

    public void onGatherData(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();

        gen.addProvider(new MyAdvProvider(gen));

        if (event.includeServer()) {
            gen.addProvider(new SetDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new AffixDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new RunewordDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new UniqueGearDatapackManager().getDataPackCreator(gen));
            gen.addProvider(new CompatibleItemDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new RuneDataPackManager().getDataPackCreator(gen));
        }

        if (event.includeClient()) {
            gen.addProvider(new ItemModelManager(gen, event.getExistingFileHelper()));
        }

    }

}

