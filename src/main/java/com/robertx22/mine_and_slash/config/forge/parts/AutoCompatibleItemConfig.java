package com.robertx22.mine_and_slash.config.forge.parts;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraftforge.common.ForgeConfigSpec;

public class AutoCompatibleItemConfig {

    public ForgeConfigSpec.BooleanValue ENABLE_AUTOMATIC_COMPATIBLE_ITEMS;

    public AutoConfigItemType TRASH;
    public AutoConfigItemType NORMAL;
    public AutoConfigItemType BEST;

    public AutoCompatibleItemConfig(ForgeConfigSpec.Builder builder) {
        builder.push("AUTO_ITEM_COMPATIBILITY");

        ENABLE_AUTOMATIC_COMPATIBLE_ITEMS = builder.define("ENABLE_AUTOMATIC_COMPATIBLE_ITEMS", false);

        TRASH = builder.comment("")
            .configure((ForgeConfigSpec.Builder b) -> {
                return new AutoConfigItemType(0.01F, b, "TRASH", 40, IRarity.Common, IRarity.Rare);
            })
            .getLeft();
        NORMAL = builder.comment("")
            .configure((ForgeConfigSpec.Builder b) -> {
                return new AutoConfigItemType(0.3F, b, "NORMAL", 80, IRarity.Common, IRarity.Legendary);
            })
            .getLeft();
        BEST = builder.comment("")
            .configure((ForgeConfigSpec.Builder b) -> {
                return new AutoConfigItemType(0.8F, b, "BEST", Integer.MAX_VALUE, IRarity.Uncommon, IRarity.Mythic);
            })
            .getLeft();

        builder.pop();
    }

}
