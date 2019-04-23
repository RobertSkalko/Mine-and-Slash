package com.robertx22.network;

import com.robertx22.mmorpg.Main;
import com.robertx22.uncommon.capability.EntityData;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PlayerUnitPackage implements IMessage {

    public PlayerUnitPackage() {

    }

    private NBTTagCompound nbt;

    public PlayerUnitPackage(NBTTagCompound nbt) {
	this.nbt = nbt;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
	NBTTagCompound tag = ByteBufUtils.readTag(buf);
	this.nbt = tag;

    }

    @Override
    public void toBytes(ByteBuf buf) {
	ByteBufUtils.writeTag(buf, nbt);
    }

    public static class Handler implements IMessageHandler<PlayerUnitPackage, IMessage> {

	@Override
	public IMessage onMessage(PlayerUnitPackage message, MessageContext ctx) {

	    Runnable noteThread = new Runnable() {
		@Override
		public void run() {
		    try {
			final EntityPlayer player = Main.proxy.getPlayerEntityFromContext(ctx);

			if (player != null) {
			    if (player.hasCapability(EntityData.Data, null)) {
				player.getCapability(EntityData.Data, null).setNBT(message.nbt);
			    }
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    };

	    noteThread.run();
	    return null;

	}
    }
}
