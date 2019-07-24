package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;

public class TooltipInfo implements Cloneable {

    public TooltipInfo(EntityCap.UnitData unitdata, MinMax minmax, int level) {
        this.minmax = minmax;
        this.level = level;
        this.unitdata = unitdata;
    }

    public TooltipInfo() {
    }

    public TooltipInfo setIsSet() {
        this.isSet = true;
        return this;
    }

    public EntityCap.UnitData unitdata;
    public MinMax minmax;
    public int level;
    public boolean isSet = false;

    public TooltipInfo withLevel(int level) {

        TooltipInfo info = null;
        try {
            info = (TooltipInfo) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        info.level = level;

        return info;

    }

}
