package com.robertx22.mine_and_slash.database.quests.actions;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;

public abstract class ActionDoneData {

    public int groupMembers = 1;

    public ActionDoneData(int groupMembers) {
        this.groupMembers = groupMembers;
    }

    public float getGroupCompletitionMultiplier() {
        return 1F / (float) groupMembers;
    }

    public static int getGroupAmount(PlayerEntity player) {

        int amount = 0;

        amount += player.world.getPlayers()
                .stream()
                .filter(x -> Load.playerMapData(x).getMap().mapUUID.equals(Load.playerMapData(player).getMap().mapUUID))
                .count();

        if (amount < 1) {
            amount = 1; // if in some weird case player isnt in world he is..? idk i feel it could happen somehow
        }

        return amount;

    }
}
