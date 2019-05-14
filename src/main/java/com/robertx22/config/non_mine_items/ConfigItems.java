package com.robertx22.config.non_mine_items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigItems {

  public ConfigItems() {

    this.map.put("modid:itemid1", new ConfigItem());
    this.map.put("modid:itemid2", new ConfigItem());
  }

  public static ConfigItems INSTANCE = new ConfigItems();

  String version = "1.0";

  public HashMap<String, ConfigItem> map = new HashMap();

  private List<ConfigItem> list = new ArrayList();

  public void refreshList() {

    list = new ArrayList<>();

    for (Map.Entry<String, ConfigItem> entry : map.entrySet()) {
      entry.getValue().registryName = entry.getKey();
      list.add(entry.getValue());

    }
  }

  public List<ConfigItem> getAll() {

    if (list.isEmpty()) {
      this.refreshList();
    }

    return list;
  }

  public void validateAll() {

    try {

      for (Map.Entry<String, ConfigItem> entry : map.entrySet()) {
        if (entry.getValue().isValid() == false) {

          System.out.println(entry.getKey()
              + " Is not correctly implemented. Please go to CompatibleItems file and fix it");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();

    }
  }


}
