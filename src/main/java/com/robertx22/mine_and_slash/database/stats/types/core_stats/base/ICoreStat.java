package com.robertx22.mine_and_slash.database.stats.types.core_stats.base;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.saveclasses.unit.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

import java.util.List;

public interface ICoreStat extends IGUID {

    void addToOtherStats(EntityCap.UnitData unit, StatData data);

    List<StatModifier> statsThatBenefit();

    float amountToReach100Percent();

}