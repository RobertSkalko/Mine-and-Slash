package com.robertx22.mine_and_slash.config.base;

import net.minecraftforge.common.ForgeConfigSpec;

public class RarityWeight {

    public ForgeConfigSpec.IntValue COMMON_WEIGHT;
    public ForgeConfigSpec.IntValue UNCOMMON_WEIGHT;
    public ForgeConfigSpec.IntValue RARE_WEIGHT;
    public ForgeConfigSpec.IntValue EPIC_WEIGHT;
    public ForgeConfigSpec.IntValue LEGENDARY_WEIGHT;
    public ForgeConfigSpec.IntValue MYTHICAL_WEIGHT;

    public RarityWeight(String prefix, ForgeConfigSpec.Builder builder) {
        builder.push(prefix);

        COMMON_WEIGHT = builder.translation("mmorpg.rarity.common")
                .defineInRange("COMMON_WEIGHT", 25000, 0, Integer.MAX_VALUE);

        UNCOMMON_WEIGHT = builder.translation("mmorpg.rarity.uncommon")
                .defineInRange("UNCOMMON_WEIGHT", 20000, 0, Integer.MAX_VALUE);

        RARE_WEIGHT = builder.translation("mmorpg.rarity.rare")
                .defineInRange("RARE_WEIGHT", 5000, 0, Integer.MAX_VALUE);

        EPIC_WEIGHT = builder.translation("mmorpg.rarity.epic")
                .defineInRange("EPIC_WEIGHT", 3000, 0, Integer.MAX_VALUE);

        LEGENDARY_WEIGHT = builder.translation("mmorpg.rarity.legendary")
                .defineInRange("LEGENDARY_WEIGHT", 1500, 0, Integer.MAX_VALUE);

        MYTHICAL_WEIGHT = builder.translation("mmorpg.rarity.mythical")
                .defineInRange("MYTHICAL_WEIGHT", 500, 0, Integer.MAX_VALUE);

        builder.pop();
    }

}