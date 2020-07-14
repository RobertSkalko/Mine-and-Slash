package com.robertx22.mine_and_slash.config.forge.parts;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class DropRatesContainer {

    public DoubleValue GEAR_DROPRATE;
    public DoubleValue CHANCE_FOR_ITEM_TO_BECOME_UNIQUE;
    public DoubleValue CURRENCY_DROPRATE;
    public DoubleValue COMPATIBLE_ITEMS_DROPRATE;

    public DropRatesContainer(ForgeConfigSpec.Builder builder) {
        builder.push("DROPRATES");

        GEAR_DROPRATE = builder.comment(".")
            .translation("mmorpg.config.gear_droprate")
            .defineInRange("GEAR_DROPRATE", 7F, 0, Integer.MAX_VALUE);

        CHANCE_FOR_ITEM_TO_BECOME_UNIQUE = builder.comment(".")
            .translation("mmorpg.config.unique_droprate")
            .defineInRange("UNIQUE_DROPRATE", 0.5F, 0, Integer.MAX_VALUE);

        CURRENCY_DROPRATE = builder.comment(".")
            .translation("mmorpg.config.currency_droprate")
            .defineInRange("CURRENCY_DROPRATE", 1.2F, 0, Integer.MAX_VALUE);

        COMPATIBLE_ITEMS_DROPRATE = builder.comment(".")
            .translation("mmorpg.config.compatible_items_droprate")
            .defineInRange("COMPATIBLE_ITEMS_DROPRATE", 2F, 0, Integer.MAX_VALUE);

        builder.pop();

    }

}
