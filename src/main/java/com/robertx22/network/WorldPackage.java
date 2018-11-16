package com.robertx22.network;

import com.robertx22.uncommon.capability.WorldData;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class WorldPackage implements IMessage {

	private NBTTagCompound nbt;

	public WorldPackage() {
	}

	public WorldPackage(NBTTagCompound nbt) {
		this.nbt = nbt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
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
						World world = DimensionManager.getWorld(message.nbt.getId());

						world.getCapability(WorldData.Data, null).setNBT(message.nbt);

					} catch (Exception e) {

					}
				}
			};
			noteThread.run();

			return null;
		}

	}

}