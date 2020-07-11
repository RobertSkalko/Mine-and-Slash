package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;

public interface ILocalStat extends IGUID {

    public default boolean IsNativeToGearType(GearItemSlot slot) {
        return slot.BaseStats()
            .stream()
            .anyMatch(x -> x.stat == this.GUID());
    }

}
