package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.blocks.scrabble.ScrabbleTile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ScrabblePacket {

    public BlockPos pos;
    public String word;

    public ScrabblePacket() {

    }

    public ScrabblePacket(BlockPos pos, String word) {
        this.pos = pos;
        this.word = word;
    }

    public static ScrabblePacket decode(PacketBuffer buf) {

        ScrabblePacket newpkt = new ScrabblePacket();

        newpkt.pos = buf.readBlockPos();
        newpkt.word = buf.readString(30);

        return newpkt;

    }

    public static void encode(ScrabblePacket packet, PacketBuffer tag) {

        tag.writeBlockPos(packet.pos);
        tag.writeString(packet.word, 30);

    }

    public static void handle(final ScrabblePacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = ctx.get()
                        .getSender();

                    if (player != null) {
                        TileEntity tile = player.world.getTileEntity(pkt.pos);

                        if (tile instanceof ScrabbleTile) {
                            ScrabbleTile scrabble = (ScrabbleTile) tile;
                            scrabble.tryGuessWord(pkt.word);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);

    }

}
