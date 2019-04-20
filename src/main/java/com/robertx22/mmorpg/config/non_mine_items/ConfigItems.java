package com.robertx22.mmorpg.config.non_mine_items;

import java.util.HashMap;

public class ConfigItems {

    public ConfigItems() {

	this.map.put("modid:itemid1", new ConfigItem());
	this.map.put("modid:itemid2", new ConfigItem());
    }

    public static ConfigItems INSTANCE = new ConfigItems();

    public HashMap<String, ConfigItem> map = new HashMap();

}
