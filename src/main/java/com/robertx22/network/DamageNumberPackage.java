package com.robertx22.network;

import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.gui.dmg_numbers.OnDisplayDamage;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DamageNumberPackage implements IMessage {

    public Elements element;
    public String string;
    public double x;
    public double y;
    public double z;
    public float height;
    public boolean isExp;

    public DamageNumberPackage() {

    }

    public DamageNumberPackage(EntityLivingBase entity, Elements ele, String str) {
	this.element = ele;
	this.string = str;
	this.x = entity.posX;
	this.y = entity.posY;
	this.z = entity.posZ;
	this.height = entity.height;

    }

    @Override
    public void fromBytes(ByteBuf buf) {
	NBTTagCompound tag = ByteBufUtils.readTag(buf);
	element = Elements.valueOf(tag.getString("element"));
	x = tag.getDouble("x");
	y = tag.getDouble("y");
	z = tag.getDouble("z");
	height = tag.getFloat("height");
	string = tag.getString("string");
	isExp = tag.getBoolean("isExp");

    }

    @Override
    public void toBytes(ByteBuf buf) {
	NBTTagCompound tag = new NBTTagCompound();
	tag.setString("element", element.name());
	tag.setDouble("x", x);
	tag.setDouble("y", y);
	tag.setDouble("z", z);
	tag.setFloat("height", height);
	tag.setString("string", string);
	tag.setBoolean("isExp", isExp);
	ByteBufUtils.writeTag(buf, tag);

    }

    public static class Handler implements IMessageHandler<DamageNumberPackage, IMessage> {

	@Override
	public IMessage onMessage(DamageNumberPackage message, MessageContext ctx) {

	    Runnable noteThread = new Runnable() {
		@Override
		public void run() {
		    try {

			if (message.isExp && ModConfig.Client.SHOW_FLOATING_EXP) {
			    OnDisplayDamage.displayParticle(message);

			} else if (message.isExp == false && ModConfig.Client.RENDER_FLOATING_DAMAGE) {
			    OnDisplayDamage.displayParticle(message);
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
