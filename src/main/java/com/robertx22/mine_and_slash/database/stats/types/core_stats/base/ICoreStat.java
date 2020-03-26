package com.robertx22.mine_and_slash.database.stats.types.core_stats.base;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

import java.util.List;

public interface ICoreStat extends IGUID {

    void addToOtherStats(EntityCap.UnitData unit, StatData data);

    List<StatMod> statsThatBenefit();

    float amountToReach100Percent();

}