package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.uncommon.gui.stat_point_screen.StatPointScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenPickStatsGuiPacket {

    public OpenPickStatsGuiPacket() {
    }

    public static OpenPickStatsGuiPacket decode(PacketBuffer buf) {

        OpenPickStatsGuiPacket newpkt = new OpenPickStatsGuiPacket();

        return newpkt;

    }

    public static void encode(OpenPickStatsGuiPacket packet, PacketBuffer tag) {

    }

    public static void handle(final OpenPickStatsGuiPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                Minecraft.getInstance().displayGuiScreen(new StatPointScreen());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}