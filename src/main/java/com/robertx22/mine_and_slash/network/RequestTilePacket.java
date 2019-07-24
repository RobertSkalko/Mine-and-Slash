package com.robertx22.mine_and_slash.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestTilePacket {

    public BlockPos pos;

    public RequestTilePacket() {

    }

    public RequestTilePacket(BlockPos pos) {
        this.pos = pos;
    }

    public static RequestTilePacket decode(PacketBuffer buf) {

        RequestTilePacket newpkt = new RequestTilePacket();

        newpkt.pos = buf.readBlockPos();

        return newpkt;

    }

    public static void encode(RequestTilePacket packet, PacketBuffer tag) {

        tag.writeBlockPos(packet.pos);

    }

    public static void handle(final RequestTilePacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    sendUpdate(pkt.pos, player);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

    private static void sendUpdate(BlockPos pos, ServerPlayerEntity player) {

        TileEntity tile = player.world.getTileEntity(pos);

        if (tile != null) {

            SUpdateTileEntityPacket supdatetileentitypacket = tile.getUpdatePacket();
            if (supdatetileentitypacket != null) {
                player.connection.sendPacket(supdatetileentitypacket);
            }
        }

    }

}
