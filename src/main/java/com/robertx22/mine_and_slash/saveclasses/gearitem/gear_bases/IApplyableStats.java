package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

public interface IApplyableStats {

    public void applyStats(EntityCap.UnitData data, int level);

    default void applyStats(EntityCap.UnitData data) {
        applyStats(data, data.getLevel());
    }

}
