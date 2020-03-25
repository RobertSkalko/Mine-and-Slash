package com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

public class StatRangeContext {

    public StatModData modData;
    public MinMax minmax = new MinMax(0, 100);
    public int level;

    public StatRangeContext(StatModData modData, MinMax minmax, int level) {
        this.modData = modData;
        this.minmax = minmax;
        this.level = level;
    }
}
