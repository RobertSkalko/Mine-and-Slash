package com.robertx22.uncommon;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.MessagePackage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class AttackUtils {

    public static void NoEnergyMessage(Entity entity) {

	if (entity instanceof EntityPlayerMP) {
	    Main.Network.sendTo(new MessagePackage("not_enough_energy", MessagePackage.MessageTypes.NoEnergy),
		    (EntityPlayerMP) entity);

	}
    }
}
