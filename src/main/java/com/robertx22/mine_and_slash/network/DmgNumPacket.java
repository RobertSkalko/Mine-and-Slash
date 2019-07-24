package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.a_libraries.dmg_number_particle.OnDisplayDamage;
import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DmgNumPacket {

    public String element;
    public String string;
    public double x;
    public double y;
    public double z;
    public float height;
    public boolean isExp;

    public DmgNumPacket() {

    }

    public DmgNumPacket(LivingEntity entity, Elements ele, String str) {
        this.element = ele.toString();
        this.string = str;
        this.x = entity.posX;
        this.y = entity.posY;
        this.z = entity.posZ;
        this.height = entity.getHeight();

    }

    public static DmgNumPacket decode(PacketBuffer buf) {

        DmgNumPacket newpkt = new DmgNumPacket();

        newpkt.element = buf.readString(10);
        newpkt.x = buf.readDouble();
        newpkt.y = buf.readDouble();
        newpkt.z = buf.readDouble();
        newpkt.height = buf.readFloat();
        newpkt.isExp = buf.readBoolean();

        newpkt.string = buf.readString(30);

        return newpkt;

    }

    public static void encode(DmgNumPacket packet, PacketBuffer tag) {

        tag.writeString(packet.element);
        tag.writeDouble(packet.x);
        tag.writeDouble(packet.y);
        tag.writeDouble(packet.z);
        tag.writeFloat(packet.height);
        tag.writeBoolean(packet.isExp);
        tag.writeString(packet.string);

    }

    public static void handle(final DmgNumPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                if (pkt.isExp && ClientContainer.INSTANCE.SHOW_FLOATING_EXP.get()) {
                    OnDisplayDamage.displayParticle(pkt);
                } else if (pkt.isExp == false && ClientContainer.INSTANCE.RENDER_FLOATING_DAMAGE
                        .get()) {
                    OnDisplayDamage.displayParticle(pkt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
