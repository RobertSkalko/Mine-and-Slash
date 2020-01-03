package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.blocks.map_device.TileMapDevice;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UseMapTicketPacket {

    public BlockPos pos;

    public UseMapTicketPacket() {

    }

    public UseMapTicketPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static UseMapTicketPacket decode(PacketBuffer buf) {

        UseMapTicketPacket newpkt = new UseMapTicketPacket();

        newpkt.pos = buf.readBlockPos();

        return newpkt;

    }

    public static void encode(UseMapTicketPacket packet, PacketBuffer tag) {

        tag.writeBlockPos(packet.pos);

    }

    public static void handle(final UseMapTicketPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    TileEntity tile = player.world.getTileEntity(pkt.pos);

                    if (tile instanceof TileMapDevice) {
                        TileMapDevice map = (TileMapDevice) tile;

                        if (map.mapDeviceData.canPlayerUse(player)) {
                            map.mapDeviceData.mapUsed.setupPlayerMapData(pkt.pos, player);
                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
