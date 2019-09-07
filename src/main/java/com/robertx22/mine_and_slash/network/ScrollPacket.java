package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ScrollPacket {
    public int row;

    public BlockPos pos;

    public ScrollPacket() {

    }

    public ScrollPacket(int row, BlockPos pos) {
        this.row = row;
        this.pos = pos;
    }

    public static ScrollPacket decode(PacketBuffer buf) {

        ScrollPacket newpkt = new ScrollPacket();

        newpkt.row = buf.readInt();
        newpkt.pos = buf.readBlockPos();

        return newpkt;

    }

    public static void encode(ScrollPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.row);
        tag.writeBlockPos(packet.pos);

    }

    public static void handle(final ScrollPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    TileEntity tile = player.world.getTileEntity(pkt.pos);

                    if (tile instanceof ProfessionTile) {
                        ProfessionTile prof = (ProfessionTile) tile;
                        prof.scrollToRow(pkt.row);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
