package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.uncommon.capability.QuestsCap;
import net.minecraft.entity.player.PlayerEntity;

public class QuestUtils {

    public static void onAction(PlayerEntity player, ActionDoneData actionData) {
        if (WorldUtils.isMapWorldClass(player.world)) {
            // make all people inside a map complete quest together
            player.world.getPlayers().forEach(x -> {
                x.getCapability(QuestsCap.Data).ifPresent(q -> q.onAction(x, actionData));
            });
        } else {
            player.getCapability(QuestsCap.Data).ifPresent(q -> q.onAction(player, actionData));
        }

    }
}
