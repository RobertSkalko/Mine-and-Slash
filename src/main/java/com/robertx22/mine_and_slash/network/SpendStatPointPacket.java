package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SpendStatPointPacket {

    public LvlPointStat stat;

    public SpendStatPointPacket() {

    }

    public SpendStatPointPacket(LvlPointStat stat) {
        this.stat = stat;

    }

    public static SpendStatPointPacket decode(PacketBuffer buf) {

        SpendStatPointPacket newpkt = new SpendStatPointPacket();

        newpkt.stat = buf.readEnumValue(LvlPointStat.class);

        return newpkt;

    }

    public static void encode(SpendStatPointPacket packet, PacketBuffer tag) {

        tag.writeEnumValue(packet.stat);

    }

    public static void handle(final SpendStatPointPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                ServerPlayerEntity player = ctx.get().getSender();
                if (player != null) {
                    Load.statPoints(player).addPoint(player, pkt.stat, Load.Unit(player));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}