package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TryRemoveTalentPacket {

    public String guid;

    public TryRemoveTalentPacket() {

    }

    public TryRemoveTalentPacket(Perk talent) {
        this.guid = talent.GUID();
    }

    public static TryRemoveTalentPacket decode(PacketBuffer buf) {

        TryRemoveTalentPacket newpkt = new TryRemoveTalentPacket();

        newpkt.guid = buf.readString(50);

        return newpkt;
    }

    public static void encode(TryRemoveTalentPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 50);
    }

    public static void handle(final TryRemoveTalentPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                PlayerTalentsCap.IPlayerTalentsData talents = Load.talents(player);

                Perk talent = SlashRegistry.Perks().get(pkt.guid);

                if (talent != null) {

                    talents.tryRemovePoint(talent);

                    MMORPG.sendToClient(new SyncCapabilityToClient(player, CapTypes.TALENTS), player);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
