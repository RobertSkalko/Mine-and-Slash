package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.config.ModConfig;
import net.minecraft.item.Item;

public class ItemUtils {
    public static Item.Properties getDefaultGearProperties() {

        Item.Properties prop = new Item.Properties();

        if (ModConfig.INSTANCE.Server.ONLY_REPAIR_IN_STATION.get()) {
            prop.setNoRepair();
        }

        return prop;
    }
}
