package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.network.proxies.OpenTalentsProxy;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenTalentsGuiPacket {

    public OpenTalentsGuiPacket() {
    }

    public static OpenTalentsGuiPacket decode(PacketBuffer buf) {

        OpenTalentsGuiPacket newpkt = new OpenTalentsGuiPacket();

        return newpkt;

    }

    public static void encode(OpenTalentsGuiPacket packet, PacketBuffer tag) {

    }

    public static void handle(final OpenTalentsGuiPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                // so it doesnt crash
                DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
                    OpenTalentsProxy.open();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}