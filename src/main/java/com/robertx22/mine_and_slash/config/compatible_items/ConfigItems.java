package com.robertx22.mine_and_slash.config.compatible_items;

import com.robertx22.mine_and_slash.config.IConfig;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.mmorpg.Ref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigItems implements IConfig, ISlashRegistryInit {

    public ConfigItems() {

        this.map.put("modid:itemid1", new ConfigItem());
        this.map.put("modid:itemid2", new ConfigItem());
    }

    public String ConfigType = "compatible_items";

    // public static ConfigItems INSTANCE = new ConfigItems();

    String version = "1.3";

    boolean enabled = true;

    public HashMap<String, ConfigItem> map = new HashMap();

    private List<ConfigItem> list = new ArrayList();

    @Override
    public void registerAll() {

        if (enabled) {

            list = new ArrayList<>();

            for (Map.Entry<String, ConfigItem> entry : map.entrySet()) {
                entry.getValue().registryName = entry.getKey();
                list.add(entry.getValue());
                System.out.println(Ref.MODID + ":Added Compatible Item: " + entry.getKey());
            }

            list.forEach(x -> x.registerToSlashRegistry());

        } else {
            System.out.println(Ref.MODID + ": Compatible Item File is marked as disabled, not loading.");
        }
    }

    public void add(String id, ConfigItem item) {
        map.put(id, item);
    }

    public void validateAll() {

        try {

            for (Map.Entry<String, ConfigItem> entry : map.entrySet()) {
                if (entry.getValue().isValid() == false) {

                    System.out.println(entry.getKey() + " Is not correctly implemented. Please go to CompatibleItems file and fix it");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public String GUID() {
        return ConfigType;
    }

}
