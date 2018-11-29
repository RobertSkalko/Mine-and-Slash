package com.robertx22.network;

import com.robertx22.mmorpg.Main;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class WorldPackage implements IMessage {

    private NBTTagCompound nbt;

    public WorldPackage() {
    }

    public WorldPackage(IWorldData data) {
	if (data != null) {
	    this.nbt = data.getNBT();
	}
    }

    @Override
    public void fromBytes(ByteBuf buf) {
	NBTTagCompound tag = ByteBufUtils.readTag(buf);
	nbt = tag;

    }

    @Override
    public void toBytes(ByteBuf buf) {
	ByteBufUtils.writeTag(buf, nbt);
    }

    public static class Handler implements IMessageHandler<WorldPackage, IMessage> {

	@Override
	public IMessage onMessage(final WorldPackage message, MessageContext ctx) {

	    Runnable noteThread = new Runnable() {
		@Override
		public void run() {
		    try {
			final EntityPlayer player = Main.proxy.getPlayerEntityFromContext(ctx);
			if (player != null) {
			    World world = player.world;
			    if (world != null) {
				world.getCapability(WorldData.Data, null).setNBT(message.nbt);
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