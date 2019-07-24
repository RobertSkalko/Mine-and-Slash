package com.robertx22.mine_and_slash.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public EntityConfigs EntityTypeConfig;
    public RarityDropratesConfig RarityWeightConfig;
    public ServerContainer Server;
    public DropRatesContainer DropRates;
    public StatConfig PlayerBaseStats;

    public static final String NAME = "MAIN_CONFIG";
    public static final ForgeConfigSpec spec;
    public static final ModConfig INSTANCE;

    static {
        final Pair<ModConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModConfig::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    ModConfig(ForgeConfigSpec.Builder builder) {

        builder.comment("Mine and Slash Config").push(NAME);

        EntityTypeConfig = builder.configure(EntityConfigs::new).getLeft();
        RarityWeightConfig = builder.configure(RarityDropratesConfig::new).getLeft();
        Server = builder.configure(ServerContainer::new).getLeft();
        DropRates = builder.configure(DropRatesContainer::new).getLeft();
        PlayerBaseStats = builder.configure(StatConfig::new).getLeft();

        builder.pop();
        //builder.build();
    }

}
