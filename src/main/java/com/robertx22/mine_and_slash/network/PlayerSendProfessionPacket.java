package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerSendProfessionPacket {

    public PlayerSendProfessionPacket() {

    }

    private CompoundNBT nbt;

    public PlayerSendProfessionPacket(ServerPlayerEntity p) {
        this.nbt = Load.professions(p).getNBT();
    }

    public static PlayerSendProfessionPacket decode(PacketBuffer buf) {

        PlayerSendProfessionPacket newpkt = new PlayerSendProfessionPacket();
        newpkt.nbt = buf.readCompoundTag();
        return newpkt;
    }

    public static void encode(PlayerSendProfessionPacket packet, PacketBuffer tag) {

        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final PlayerSendProfessionPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                final PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

                if (player != null) {
                    Load.professions(player).setNBT(pkt.nbt);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
