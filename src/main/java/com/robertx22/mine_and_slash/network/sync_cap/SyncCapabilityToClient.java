package com.robertx22.mine_and_slash.network.sync_cap;

import com.robertx22.mine_and_slash.mmorpg.CapSyncCheck;
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
    private CapTypes type;

    public SyncCapabilityToClient(ServerPlayerEntity p, CapTypes type) {
        this.nbt = type.getCap(p).getNBT();
        this.type = type;
    }

    public static SyncCapabilityToClient decode(PacketBuffer buf) {

        SyncCapabilityToClient newpkt = new SyncCapabilityToClient();
        newpkt.nbt = buf.readCompoundTag();
        newpkt.type = buf.readEnumValue(CapTypes.class);
        return newpkt;
    }

    public static void encode(SyncCapabilityToClient packet, PacketBuffer tag) {

        tag.writeCompoundTag(packet.nbt);
        tag.writeEnumValue(packet.type);

    }

    public static void handle(final SyncCapabilityToClient pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                final PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

                if (player != null) {
                    pkt.type.getCap(player).setNBT(pkt.nbt);

                    CapSyncCheck.set(pkt.type);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}

