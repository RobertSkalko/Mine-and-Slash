package com.robertx22.mine_and_slash.config.compatible_items;

import com.robertx22.mine_and_slash.config.base.IConfig;
import com.robertx22.mine_and_slash.data_packs.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OldConfigItems implements IConfig, ISlashRegistryInit {

    public static List<CompatibleItem> newFormatList = new ArrayList<>();

    public OldConfigItems() {

        this.map.put("modid:itemid1", new OldConfigItem());
        this.map.put("modid:itemid2", new OldConfigItem());
    }

    public String ConfigType = "compatible_items";

    String version = "1.3";

    boolean enabled = true;

    public HashMap<String, OldConfigItem> map = new HashMap();

    private List<OldConfigItem> list = new ArrayList();

    @Override
    public void registerAll() {

        if (enabled) {

            list = new ArrayList<>();

            for (Map.Entry<String, OldConfigItem> entry : map.entrySet()) {
                entry.getValue().registryName = entry.getKey();
                list.add(entry.getValue());
            }

            list.forEach(x -> newFormatList.add(x.convertToNewFormat()));

        } else {
            System.out.println(Ref.MODID + ": Compatible Item File is marked as disabled, not loading.");
        }
    }

    public void add(String id, OldConfigItem item) {
        map.put(id, item);
    }

    @Override
    public String GUID() {
        return ConfigType;
    }

}
