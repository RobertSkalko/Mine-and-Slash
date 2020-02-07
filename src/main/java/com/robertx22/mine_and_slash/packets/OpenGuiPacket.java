package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.packets.proxies.OpenGuiWrapper;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenGuiPacket {

    public enum GuiType {
        TALENTS,
        PICK_STATS,
        SPELLS,
        MAIN_HUB
    }

    GuiType type;

    private OpenGuiPacket() {

    }

    public OpenGuiPacket(GuiType type) {
        this.type = type;
    }

    public static OpenGuiPacket decode(PacketBuffer buf) {
        OpenGuiPacket newpkt = new OpenGuiPacket();
        newpkt.type = GuiType.valueOf(buf.readString(20));
        return newpkt;

    }

    public static void encode(OpenGuiPacket packet, PacketBuffer tag) {

        tag.writeString(packet.type.name(), 20);

    }

    public static void handle(final OpenGuiPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
                    if (pkt.type == GuiType.PICK_STATS) {
                        OpenGuiWrapper.openStatAllocation();
                    }
                    if (pkt.type == GuiType.TALENTS) {
                        OpenGuiWrapper.openTalents();
                    }
                    if (pkt.type == GuiType.SPELLS) {
                        OpenGuiWrapper.openSpellPerks();
                    }
                    if (pkt.type == GuiType.MAIN_HUB) {
                        OpenGuiWrapper.openMainHub();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}