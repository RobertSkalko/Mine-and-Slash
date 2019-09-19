package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SpendStatPointPacket {
    public BlockPos pos;
    public String statmodguid;

    public SpendStatPointPacket() {

    }

    public SpendStatPointPacket(String statmodguid) {
        this.statmodguid = statmodguid;

    }

    public SpendStatPointPacket(BlockPos pos) {
        this.pos = pos;
        this.statmodguid = "";

    }

    public static SpendStatPointPacket decode(PacketBuffer buf) {

        SpendStatPointPacket newpkt = new SpendStatPointPacket();

        newpkt.statmodguid = buf.readString();

        return newpkt;

    }

    public static void encode(SpendStatPointPacket packet, PacketBuffer tag) {

        tag.writeString(packet.statmodguid);

    }

    public static void handle(final SpendStatPointPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                ServerPlayerEntity player = ctx.get().getSender();
                if (player != null) {
                    Load.statPoints(player).addPoint(pkt.statmodguid, Load.Unit(player));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}