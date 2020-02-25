package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OnLoginClientPacket {

    public OnLoginClientPacket() {

    }

    public static OnLoginClientPacket decode(PacketBuffer buf) {
        return new OnLoginClientPacket();

    }

    public static void encode(OnLoginClientPacket packet, PacketBuffer tag) {

    }

    public static void handle(final OnLoginClientPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {
                    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

                        SlashRegistry.backup();
                        ConfigRegister.unregisterFlaggedEntries();
                        SlashRegistry.getAllRegistries()
                            .forEach(x -> x.
                                unregisterAllEntriesFromDatapacks());
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);

    }

}