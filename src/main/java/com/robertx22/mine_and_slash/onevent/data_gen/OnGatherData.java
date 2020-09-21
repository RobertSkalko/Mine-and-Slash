package com.robertx22.mine_and_slash.onevent.data_gen;

import com.robertx22.mine_and_slash.data_generation.affixes.AffixDataPackManager;
import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItemDataPackManager;
import com.robertx22.mine_and_slash.data_generation.loot_tables.ModLootTableProvider;
import com.robertx22.mine_and_slash.data_generation.mob_affixes.MobAffixDataPackManager;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.data_generation.rarities.GearRarityManager;
import com.robertx22.mine_and_slash.data_generation.runes.RuneDataPackManager;
import com.robertx22.mine_and_slash.data_generation.runewords.RunewordDataPackManager;
import com.robertx22.mine_and_slash.data_generation.sets.SetDataPackManager;
import com.robertx22.mine_and_slash.data_generation.tiers.TierDatapackManager;
import com.robertx22.mine_and_slash.data_generation.unique_gears.UniqueGearDatapackManager;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.MyAdvProvider;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class OnGatherData {

    public void onGatherData(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();

        gen.addProvider(new MyAdvProvider(gen));

        if (event.includeServer()) {

            gen.addProvider(new MobAffixDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new TierDatapackManager().getDataPackCreator(gen));
            gen.addProvider(new SetDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new AffixDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new RunewordDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new UniqueGearDatapackManager().getDataPackCreator(gen));
            gen.addProvider(new CompatibleItemDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new RuneDataPackManager().getDataPackCreator(gen));

            gen.addProvider(new SlashRecipeProvider(gen));

            gen.addProvider(new GearRarityManager().getProvider(gen));

            gen.addProvider(new ModLootTableProvider(gen));

        }

        if (event.includeClient()) {
            gen.addProvider(new ItemModelManager(gen, event.getExistingFileHelper()));
        }

    }

}

