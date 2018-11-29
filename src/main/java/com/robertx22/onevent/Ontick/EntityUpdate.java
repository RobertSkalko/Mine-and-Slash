package com.robertx22.onevent.ontick;

import java.util.List;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.EntityPackage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityUpdate {
    public EntityUpdate(EntityPlayerMP player, List<EntityLivingBase> entities) {
	this.player = player;
	this.entities = entities;
    }

    public EntityPlayerMP player;
    public List<EntityLivingBase> entities;
    public int current = 0;

    public boolean isFinished() {
	return current >= entities.size();
    }

    public void update() {

	try {
	    if (!isFinished()) {

		EntityPackage mobpacket = new EntityPackage(entities.get(current++));
		Main.Network.sendTo(mobpacket, (EntityPlayerMP) player);

	    }
	} catch (Exception e) {

	    e.printStackTrace();
	}

    }

    public static boolean IsEntityCloseTo(EntityLivingBase first, EntityLivingBase second) {

	double distance = first.getDistance(second);

	return distance < 150;

    }

    public static void syncEntityToClient(EntityLivingBase entity) {

	EntityPackage mobpacket = new EntityPackage(entity);

	Main.Network.sendToAllAround(mobpacket,
		new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 150));

    }

}