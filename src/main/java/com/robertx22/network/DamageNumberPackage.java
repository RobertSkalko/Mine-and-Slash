package com.robertx22.network;

import com.robertx22.saveclasses.DamageNumberData;
import com.robertx22.uncommon.datasaving.bases.Saving;
import com.robertx22.uncommon.gui.dmg_numbers.OnDisplayDamage;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DamageNumberPackage implements IMessage {

	public String toSend;

	public DamageNumberPackage() {

	}

	public DamageNumberPackage(String str) {
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

	public static class Handler implements IMessageHandler<DamageNumberPackage, IMessage> {

		@Override
		public IMessage onMessage(DamageNumberPackage message, MessageContext ctx) {

			try {

				DamageNumberData data = Saving.Load(message.toSend, DamageNumberData.class);

				OnDisplayDamage.displayParticle(data);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
	}

}
