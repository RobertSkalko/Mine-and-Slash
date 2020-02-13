package com.robertx22.mine_and_slash.onevent.data_gen;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.MyAdvProvider;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class OnGatherData {

    public void onGatherData(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();

        gen.addProvider(new MyAdvProvider(gen));

        gen.addProvider(new SlashDataProvider<StatMod>(gen, SlashRegistry.StatMods().getSerializable(), "stat_mods"));

    }

}

