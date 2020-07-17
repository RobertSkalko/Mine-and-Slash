package com.robertx22.mine_and_slash.vanilla_mc.packets.sync_cap;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncCapabilityToClient {

    public SyncCapabilityToClient() {

    }

    private CompoundNBT nbt;
    private PlayerCaps type;

    public SyncCapabilityToClient(ServerPlayerEntity p, PlayerCaps type) {
        this.nbt = type.getCap(p)
            .saveToNBT();
        this.type = type;
    }

    public static SyncCapabilityToClient decode(PacketBuffer buf) {

        SyncCapabilityToClient newpkt = new SyncCapabilityToClient();
        newpkt.nbt = buf.readCompoundTag();
        newpkt.type = buf.readEnumValue(PlayerCaps.class);
        return newpkt;
    }

    public static void encode(SyncCapabilityToClient packet, PacketBuffer tag) {

        tag.writeCompoundTag(packet.nbt);
        tag.writeEnumValue(packet.type);

    }

    public static void handle(final SyncCapabilityToClient pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {
                    final PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

                    if (player != null) {
                        pkt.type.getCap(player)
                            .loadFromNBT(pkt.nbt);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);

    }

}

