package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.NoEnergyPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class AttackUtils {

    public static void NoEnergyMessage(Entity entity) {

	if (entity instanceof ServerPlayerEntity) {

	    MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) entity);

	}
    }
}
