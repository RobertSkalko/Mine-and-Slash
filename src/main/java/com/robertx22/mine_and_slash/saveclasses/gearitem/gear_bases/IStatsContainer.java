package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

import java.util.List;

public interface IStatsContainer extends IApplyableStats {

    public List<StatData> GetAllStats(int level);

    @Override
    default void applyStats(EntityCap.UnitData data) {
        GetAllStats(data.getLevel()).forEach(x -> {
            if (x.isNotEmpty()) {
                data.getUnit().getCreateStat(x.getId()).addExact(x);
            }
        });
    }
}
