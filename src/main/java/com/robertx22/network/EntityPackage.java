package com.robertx22.network;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.datasaving.bases.Saving;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EntityPackage implements IMessage {

	private String toSend;

	public EntityPackage() {

	}

	public EntityPackage(String str) {
		this.toSend = str;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		toSend = ByteBufUtils.readUTF8String(buf);

	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, toSend);

	}

	public static class Handler implements IMessageHandler<EntityPackage, IMessage> {

		@Override
		public IMessage onMessage(EntityPackage message, MessageContext ctx) {

			try {
				Unit unit = Saving.Load(message.toSend, Unit.class);
				final EntityPlayer player = Minecraft.getMinecraft().player;

				if (unit != null && unit.uid != null) {
					for (Entity en : player.world.loadedEntityList) {

						if (en.getUniqueID().equals(unit.uid)) {
							UnitSaving.Save(en, unit);
						}
					}
				}
			} catch (Exception e) {
			}

			return null;
		}

	}

}
