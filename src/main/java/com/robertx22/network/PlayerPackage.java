package com.robertx22.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PlayerPackage implements IMessage {

	public String toSend;

	public PlayerPackage() {

	}

	public PlayerPackage(String str) {
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

}
