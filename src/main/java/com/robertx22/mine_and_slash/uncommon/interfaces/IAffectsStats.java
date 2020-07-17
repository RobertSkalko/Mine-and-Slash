package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.database.data.IGUID;
import com.robertx22.mine_and_slash.saveclasses.unit.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

public interface IAffectsStats extends IGUID {
    void affectStats(EntityCap.UnitData data, StatData statData);

}
