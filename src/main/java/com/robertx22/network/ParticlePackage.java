package com.robertx22.network;

import com.robertx22.database.lists.ParticleGens;
import com.robertx22.database.particle_gens.ParticleGen;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ParticlePackage implements IMessage {

	private String name;
	private double x;
	private double y;
	private double z;
	private double xVel;
	private double yVel;
	private double zVel;
	private boolean isGenerator;

	public ParticlePackage() {
	}

	public ParticlePackage(boolean isGen, String name, double x, double y, double z, double xVel, double yVel,
			double zVel) {
		this.isGenerator = isGen;
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.xVel = xVel;
		this.yVel = yVel;
		this.zVel = zVel;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		isGenerator = tag.getBoolean("isGen");
		name = tag.getString("name");
		x = tag.getDouble("x");
		y = tag.getDouble("y");
		z = tag.getDouble("z");
		xVel = tag.getDouble("xVel");
		yVel = tag.getDouble("yVel");
		zVel = tag.getDouble("zVel");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("isGen", isGenerator);
		tag.setString("name", name);
		tag.setDouble("x", x);
		tag.setDouble("y", y);
		tag.setDouble("z", z);
		tag.setDouble("xVel", xVel);
		tag.setDouble("yVel", yVel);
		tag.setDouble("zVel", zVel);
		ByteBufUtils.writeTag(buf, tag);
	}

	public static class Handler implements IMessageHandler<ParticlePackage, IMessage> {

		@Override
		public IMessage onMessage(final ParticlePackage message, MessageContext ctx) {

			Runnable noteThread = new Runnable() {
				@Override
				public void run() {
					try {

						if (message.isGenerator) {

							ParticleGen gen = ParticleGens.All.get(message.name);

							gen.Summon(message.x, message.y, message.z, message.xVel, message.yVel, message.zVel);

						} else {

							EnumParticleTypes particle = EnumParticleTypes.getByName(message.name);
							World world = Minecraft.getMinecraft().world;

							world.spawnParticle(particle, message.x, message.y, message.z, message.xVel, message.yVel,
									message.zVel);
						}
					} catch (Exception e) {

					}
				}
			};
			noteThread.run();

			return null;
		}

	}

}