package com.robertx22.mine_and_slash.config.forge.parts;

import net.minecraftforge.common.ForgeConfigSpec;

public class AutoCompatibleItemConfig {

    public ForgeConfigSpec.BooleanValue ENABLE_AUTOMATIC_COMPATIBLE_ITEMS;

    public AutoCompatibleItemConfig(ForgeConfigSpec.Builder builder) {
        builder.push("AUTO_ITEM_COMPATIBILITY");

        ENABLE_AUTOMATIC_COMPATIBLE_ITEMS = builder.define("ENABLE_AUTOMATIC_COMPATIBLE_ITEMS", false);

        builder.pop();
    }

}
