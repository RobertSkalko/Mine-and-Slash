package com.robertx22.uncommon;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.MessagePackage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextFormatting;

public class AttackUtils {

    public static void NoEnergyMessage(EntityLivingBase entity) {

	if (entity instanceof EntityPlayerMP) {
	    Main.Network.sendTo(
		    new MessagePackage(TextFormatting.RED + "Not Enough Energy.", MessagePackage.MessageTypes.NoEnergy),
		    (EntityPlayerMP) entity);

	}
    }
}
