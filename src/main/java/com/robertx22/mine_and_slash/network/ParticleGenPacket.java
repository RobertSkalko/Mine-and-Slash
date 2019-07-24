package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.database.particle_gens.ParticleGen;
import com.robertx22.mine_and_slash.db_lists.ParticleGens;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IColor;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticleGenPacket {

    private String name;
    private double x;
    private double y;
    private double z;
    private double xVel;
    private double yVel;
    private double zVel;
    private double radius;
    private int amount;
    private Elements.RGB color;

    public ParticleGenPacket() {
    }

    public ParticleGenPacket(String name, double x, double y, double z, double xVel,
                             double yVel, double zVel, double radius, int amount,
                             IColor icolor) {

        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xVel = xVel;
        this.yVel = yVel;
        this.zVel = zVel;
        this.radius = radius;
        this.amount = amount;
        this.color = icolor.getRGBColor();

    }

    public static ParticleGenPacket decode(PacketBuffer tag) {

        ParticleGenPacket newpkt = new ParticleGenPacket();

        newpkt.name = tag.readString(30);
        newpkt.x = tag.readDouble();
        newpkt.y = tag.readDouble();
        newpkt.z = tag.readDouble();
        newpkt.xVel = tag.readDouble();
        newpkt.yVel = tag.readDouble();
        newpkt.zVel = tag.readDouble();
        newpkt.radius = tag.readDouble();
        newpkt.amount = tag.readInt();

        int r = tag.readInt();
        int g = tag.readInt();
        int b = tag.readInt();

        newpkt.color = new Elements.RGB(r, g, b);

        return newpkt;

    }

    public static void encode(ParticleGenPacket packet, PacketBuffer tag) {

        tag.writeString(packet.name, 30);
        tag.writeDouble(packet.x);
        tag.writeDouble(packet.y);
        tag.writeDouble(packet.z);
        tag.writeDouble(packet.xVel);
        tag.writeDouble(packet.yVel);
        tag.writeDouble(packet.zVel);
        tag.writeDouble(packet.radius);
        tag.writeInt(packet.amount);
        tag.writeInt(packet.color.getIntR());
        tag.writeInt(packet.color.getIntG());
        tag.writeInt(packet.color.getIntB());

    }

    public static void handle(final ParticleGenPacket message,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ParticleGen gen = ParticleGens.All.get(message.name);

                gen.Summon(message.x, message.y, message.z, message.radius, message.amount, message.color);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}