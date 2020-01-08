package com.robertx22.mine_and_slash.quests.actions;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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

    public static int getGroupAmount(PlayerEntity player) {

        int amount = 0;

        amount += player.world.getPlayers()
                .stream()
                .filter(x -> Load.playerMapData(x)
                        .getMap().mapUUID.equals(Load.playerMapData(player)
                                .getMap().mapUUID))
                .count();

        if (amount < 1) {
            amount = 1; // if in some weird case player isnt in world he is..? idk i feel it could happen somehow
        }

        return amount;

    }

    public KilledMobData(Entity mobkilled, EntityCap.UnitData mobData,
                         PlayerEntity player, EntityCap.UnitData playerData) {
        super(getGroupAmount(player));
        this.mobkKilled = mobkilled;
        this.mobData = mobData;

        this.player = player;
        this.playerData = playerData;

    }

}
