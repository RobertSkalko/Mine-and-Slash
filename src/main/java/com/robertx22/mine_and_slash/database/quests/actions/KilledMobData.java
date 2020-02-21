package com.robertx22.mine_and_slash.database.quests.actions;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class KilledMobData extends ActionDoneData {

    public Entity mobkKilled;
    public EntityCap.UnitData mobData;
    public PlayerEntity player;
    public EntityCap.UnitData playerData;

    private KilledMobData(Entity mobkilled, PlayerEntity player) {
        super(getGroupAmount(player));
        this.mobkKilled = mobkilled;
        this.player = player;
    }

    public KilledMobData(Entity mobkilled, EntityCap.UnitData mobData, PlayerEntity player,
                         EntityCap.UnitData playerData) {
        super(getGroupAmount(player));
        this.mobkKilled = mobkilled;
        this.mobData = mobData;

        this.player = player;
        this.playerData = playerData;

    }

}
