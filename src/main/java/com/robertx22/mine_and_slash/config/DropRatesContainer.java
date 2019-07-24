package com.robertx22.mine_and_slash.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class DropRatesContainer {

    public DoubleValue RUNED_GEAR_DROPRATE;
    public DoubleValue RUNE_DROPRATE;
    public DoubleValue MAP_DROPRATE;
    public DoubleValue GEAR_DROPRATE;
    public DoubleValue UNIQUE_DROPRATE;
    public DoubleValue SPELL_DROPRATE;
    public DoubleValue CURRENCY_DROPRATE;
    public DoubleValue AWAKEN_RUNEWORD_DROPRATE;
    public DoubleValue COMPATIBLE_ITEMS_DROPRATE;
    public DoubleValue LOOTBOX_DROPRATE;

    DropRatesContainer(ForgeConfigSpec.Builder builder) {
        builder.push("DROPRATES");

        RUNED_GEAR_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.runed_gear_droprate")
                .defineInRange("RUNED_GEAR_DROPRATE", 1.8D, 0, Integer.MAX_VALUE);

        LOOTBOX_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.lootbox_droprate")
                .defineInRange("LOOTBOX_DROPRATE", 0.05D, 0, Integer.MAX_VALUE);

        RUNE_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.rune_droprate")
                .defineInRange("RUNE_DROPRATE", 1.4D, 0, Integer.MAX_VALUE);

        MAP_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.map_droprate")
                .defineInRange("MAP_DROPRATE", 0.8F, 0, Integer.MAX_VALUE);

        GEAR_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.gear_droprate")
                .defineInRange("GEAR_DROPRATE", 7.4F, 0, Integer.MAX_VALUE);

        UNIQUE_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.unique_droprate")
                .defineInRange("UNIQUE_DROPRATE", 0.15F, 0, Integer.MAX_VALUE);

        SPELL_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.spell_droprate")
                .defineInRange("SPELL_DROPRATE", 4F, 0, Integer.MAX_VALUE);

        CURRENCY_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.currency_droprate")
                .defineInRange("CURRENCY_DROPRATE", 3F, 0, Integer.MAX_VALUE);

        AWAKEN_RUNEWORD_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.awaken_runeword_droprate")
                .defineInRange("AWAKEN_RUNEWORD_DROPRATE", 0.15F, 0, Integer.MAX_VALUE);

        COMPATIBLE_ITEMS_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.compatible_items_droprate")
                .defineInRange("COMPATIBLE_ITEMS_DROPRATE", 2F, 0, Integer.MAX_VALUE);

        builder.pop();

    }
}
