package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerMapPacket {

    public PlayerMapPacket() {

    }

    private CompoundNBT nbt;

    public PlayerMapPacket(ServerPlayerEntity p) {
        this.nbt = Load.playerMapData(p).getNBT();
    }

    public static PlayerMapPacket decode(PacketBuffer buf) {

        PlayerMapPacket newpkt = new PlayerMapPacket();
        newpkt.nbt = buf.readCompoundTag();
        return newpkt;
    }

    public static void encode(PlayerMapPacket packet, PacketBuffer tag) {

        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final PlayerMapPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                final PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

                if (player != null) {

                    Load.playerMapData(player).setNBT(pkt.nbt);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}

