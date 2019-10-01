package com.robertx22.mine_and_slash.database.items.unique_items;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatRequirementsData;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;

import java.util.HashMap;
import java.util.Map;

public class StatReq {

    public StatReq(LvlPointStat stat, Size size) {
        map.put(stat.statguid, size);
    }

    public StatReq(LvlPointStat stat, Size size, LvlPointStat stat2, Size size2) {
        map.put(stat.statguid, size);
        map.put(stat2.statguid, size2);
    }

    public enum Size {
        TINY(0.25F),
        SMALL(0.5F),
        MEDIUM(0.75F),
        NORMAL(1F),
        MAJOR(1.25F),
        HUGE(1.5F);

        public float multi;

        Size(float multi) {
            this.multi = multi;
        }
    }

    private HashMap<String, Size> map = new HashMap<>();

    public HashMap<String, Integer> getRequirements(int lvl, GearRarity rar) {
        HashMap<String, Integer> hashmap = new HashMap<>();

        for (Map.Entry<String, Size> entry : map.entrySet()) {
            int val = (int) ((entry.getValue().multi * StatRequirementsData.getAmount(lvl)) * rar
                    .requirementMulti());
            hashmap.put(entry.getKey(), val);
        }

        return hashmap;
    }

}
