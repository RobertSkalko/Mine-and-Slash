package com.robertx22.network;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.datasaving.bases.Saving;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class StringPackage implements IMessage {

	private String toSend;

	public StringPackage() {

	}

	public StringPackage(String str) {
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

	public static class Handler implements IMessageHandler<StringPackage, IMessage> {

		@Override
		public IMessage onMessage(StringPackage message, MessageContext ctx) {

			Unit unit = Saving.Load(message.toSend, Unit.class);
			final EntityPlayer player = Minecraft.getMinecraft().player;
			UnitSaving.Save(player, unit);

			return null;
		}

	}

}
