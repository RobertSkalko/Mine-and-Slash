package com.robertx22.api.msg_types;

import com.robertx22.config.non_mine_items.ConfigItem;
import com.robertx22.config.non_mine_items.ConfigItems;

public class CompatibleItemMSG implements MineAndSlashMSG {

    public ConfigItem configItem;
    public String itemID;

    public CompatibleItemMSG(ConfigItem configItem, String itemID) {
        this.configItem = configItem;
        this.itemID = itemID;
    }

    @Override
    public final void register() {
        ConfigItems.INSTANCE.map.put(itemID, configItem);
        ConfigItems.INSTANCE.refreshList();
    }

}
