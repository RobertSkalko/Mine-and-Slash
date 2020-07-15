package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseGearType;

public interface ILocalStat extends IGUID {

    public default boolean IsNativeToGearType(BaseGearType slot) {
        return slot.baseStats()
            .stream()
            .anyMatch(x -> x.stat == this.GUID());
    }

}
