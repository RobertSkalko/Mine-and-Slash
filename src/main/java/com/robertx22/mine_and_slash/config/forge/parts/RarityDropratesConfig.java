package com.robertx22.mine_and_slash.config.forge.parts;

import com.robertx22.mine_and_slash.config.base.RarityWeight;
import net.minecraftforge.common.ForgeConfigSpec;

public class RarityDropratesConfig {

    public RarityWeight RUNED_ITEMS;
    public RarityWeight RUNES;
    public RarityWeight MOBS;
    public RarityWeight MAPS;
    public RarityWeight CURRENCY;

    public RarityDropratesConfig(ForgeConfigSpec.Builder builder) {
        builder.push("RARITY_WEIGHT_CONTAINERS");

        RarityWeight.DefaultConfig config = new RarityWeight.DefaultConfig();

        RUNED_ITEMS = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("RUNED_ITEMS", builder,
            new RarityWeight.DefaultConfig()
        ))
            .getLeft();

        RUNES = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("RUNES", builder,
            new RarityWeight.DefaultConfig().higherChanceByMulti(
                1.5F)
        ))
            .getLeft();

        MOBS = builder.configure(
            (ForgeConfigSpec.Builder prefix) -> new RarityWeight("MOBS", builder, new RarityWeight.DefaultConfig()))
            .getLeft();

        MAPS = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("MAPS", builder,
            new RarityWeight.DefaultConfig().higherChanceByMulti(
                1.5F)
        ))
            .getLeft();

        CURRENCY = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("CURRENCY", builder,
            new RarityWeight.DefaultConfig()
        ))
            .getLeft();

        builder.pop();
    }

}
