package com.robertx22.mine_and_slash.database.stats.types.core_stats.base;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

public interface IPreCoreStat extends IGUID {

    void addToCoreStats(EntityCap.UnitData unit, StatData data);

}
