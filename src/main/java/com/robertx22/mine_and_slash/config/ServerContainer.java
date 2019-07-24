package com.robertx22.mine_and_slash.config;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class ServerContainer {

    public BooleanValue USE_COMPATIBILITY_ITEMS;
    public BooleanValue DISABLE_VANILLA_HP_REGEN;
    public BooleanValue GENERATE_ORES;
    public BooleanValue LEVEL_UPS_COST_TOKEN;
    public BooleanValue USE_ATTACK_COOLDOWN;
    public BooleanValue GET_STARTER_ITEMS;
    public BooleanValue RESET_MAP_DIMENSIONS_ON_LOAD;
    public BooleanValue DISABLE_DEATH_IN_MAPS;

    public IntValue MAXIMUM_WORN_RUNED_ITEMS;
    public IntValue MAXIMUM_WORN_UNIQUE_ITEMS;
    public IntValue MAX_PLAYERS_PER_MAP;
    public IntValue MAPS_DROP_AFTER_LEVEL;
    public IntValue CURRENCY_DROP_AFTER_LEVEL;
    public IntValue MAXIMUM_PLAYER_LEVEL;

    public DoubleValue NON_MOD_DAMAGE_MULTI;
    public DoubleValue MOB_ENVIRONMENT_DAMAGE_MULTI;
    public DoubleValue NON_MOD_HEAL_MULTI;
    public DoubleValue EXPERIENCE_MULTIPLIER;
    public DoubleValue UNARMED_ENERGY_COST;
    public DoubleValue STOP_DROPS_IF_NON_PLAYER_DOES_DMG_PERCENT;
    public DoubleValue PLAYER_HEART_TO_HEALTH_CONVERSION;

    ServerContainer(Builder builder) {
        builder.push("GENERAL");

        PLAYER_HEART_TO_HEALTH_CONVERSION = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("PLAYER_HEART_TO_HEALTH_CONVERSION", 1D, 0D, 100D);

        USE_ATTACK_COOLDOWN = builder.comment(".")
                .translation("mmorpg.word")
                .define("USE_ATTACK_COOLDOWN", true);

        RESET_MAP_DIMENSIONS_ON_LOAD = builder.comment(".")
                .translation("mmorpg.word")
                .define("RESET_MAP_DIMENSIONS_ON_LOAD", true);

        DISABLE_DEATH_IN_MAPS = builder.comment(".")
                .translation("mmorpg.word")
                .define("DISABLE_DEATH_IN_MAPS", true);

        GET_STARTER_ITEMS = builder.comment(".")
                .translation("mmorpg.word")
                .define("GET_STARTER_ITEMS", true);

        DISABLE_VANILLA_HP_REGEN = builder.comment(".")
                .translation("mmorpg.word.entities")
                .define("DISABLE_VANILLA_HP_REGEN", true);

        USE_COMPATIBILITY_ITEMS = builder.comment(".")
                .translation("mmorpg.word.")
                .define("USE_COMPATIBILITY_ITEMS", true);

        GENERATE_ORES = builder.comment(".")
                .translation("mmorpg.word.")
                .define("GENERATE_ORES", true);

        LEVEL_UPS_COST_TOKEN = builder.comment(".")
                .translation("mmorpg.word.")
                .define("LEVEL_UPS_COST_TOKEN", false);

        MAXIMUM_WORN_RUNED_ITEMS = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAXIMUM_WORN_RUNED_ITEMS", 3, 0, Integer.MAX_VALUE);

        MAXIMUM_WORN_UNIQUE_ITEMS = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAXIMUM_WORN_UNIQUE_ITEMS", 4, 0, Integer.MAX_VALUE);

        MAPS_DROP_AFTER_LEVEL = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAPS_DROP_AFTER_LEVEL", 10, 0, Integer.MAX_VALUE);

        CURRENCY_DROP_AFTER_LEVEL = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("CURRENCY_DROP_AFTER_LEVEL", 10, 0, Integer.MAX_VALUE);

        MAXIMUM_PLAYER_LEVEL = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAXIMUM_PLAYER_LEVEL", 100, 0, Integer.MAX_VALUE);

        NON_MOD_DAMAGE_MULTI = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("NON_MOD_DAMAGE_MULTI", 0.03D, 0D, Integer.MAX_VALUE);

        STOP_DROPS_IF_NON_PLAYER_DOES_DMG_PERCENT = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("STOP_DROPS_IF_NON_PLAYER_DOES_DMG_PERCENT", 0.5D, 0D, 1D);

        MOB_ENVIRONMENT_DAMAGE_MULTI = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MOB_ENVIRONMENT_DAMAGE_MULTI", 0.2D, 0D, Integer.MAX_VALUE);

        NON_MOD_HEAL_MULTI = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("NON_MOD_HEAL_MULTI", 0.1D, 0D, Integer.MAX_VALUE);

        EXPERIENCE_MULTIPLIER = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("EXPERIENCE_MULTIPLIER", 1D, 0D, Integer.MAX_VALUE);

        UNARMED_ENERGY_COST = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("UNARMED_ENERGY_COST", 1D, 0D, Integer.MAX_VALUE);

        builder.pop();
    }

}
