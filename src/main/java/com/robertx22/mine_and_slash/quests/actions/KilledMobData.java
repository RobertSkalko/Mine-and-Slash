package com.robertx22.mine_and_slash.quests.actions;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.Entity;

public class KilledMobData extends ActionDoneData {

    Entity mobkKilled;
    EntityCap.UnitData mobData;

    public KilledMobData(Entity mobkilled) {
        this.mobkKilled = mobkilled;
    }

    public KilledMobData(Entity mobkilled, EntityCap.UnitData mobData) {
        this.mobkKilled = mobkilled;
        this.mobData = mobData;
    }

}
