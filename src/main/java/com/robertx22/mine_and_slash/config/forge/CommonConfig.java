package com.robertx22.mine_and_slash.config.forge;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {
    public static final String NAME = "COMMON";
    public static final ForgeConfigSpec spec;
    public static final CommonConfig INSTANCE;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(
            CommonConfig::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    public ForgeConfigSpec.BooleanValue GET_STARTER_ITEMS;

    CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Common Settings")
            .push(NAME);

        GET_STARTER_ITEMS = builder.comment(".")
            .translation("mmorpg.word")
            .define("GET_STARTER_ITEMS", true);

        builder.pop();
    }

}
