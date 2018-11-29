package com.robertx22.network;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.robertx22.mmorpg.Main;
import com.robertx22.uncommon.capability.EntityData;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EntityPackage implements IMessage {

    public String uuid;
    public NBTTagCompound nbt;

    public EntityPackage() {

    }

    public EntityPackage(EntityLivingBase entity) {
	this.uuid = entity.getUniqueID().toString();
	this.nbt = entity.getCapability(EntityData.Data, null).getNBT();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
	nbt = ByteBufUtils.readTag(buf);
	uuid = nbt.getString("uuid");
    }

    @Override
    public void toBytes(ByteBuf buf) {
	nbt.setString("uuid", uuid);
	ByteBufUtils.writeTag(buf, nbt);

    }

    public static class Handler implements IMessageHandler<EntityPackage, IMessage> {

	@Override
	public IMessage onMessage(EntityPackage message, MessageContext ctx) {

	    try {

		final EntityPlayer player = Main.proxy.getPlayerEntityFromContext(ctx);

		if (player.world != null && player.world != null && player.world.loadedEntityList != null) {
		    List<Entity> entities = new ArrayList<Entity>(player.world.loadedEntityList);
		    for (Entity en : entities) {
			if (en.getUniqueID().equals(UUID.fromString(message.uuid))) {
			    en.getCapability(EntityData.Data, null).setNBT(message.nbt);
			    break;
			}

		    }

		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    return null;
	}

    }
}
