package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
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

    SlashRegistryType registryType;

    public TryRemoveTalentPacket() {

    }

    public TryRemoveTalentPacket(Perk talent) {
        this.guid = talent.GUID();
        registryType = talent.getSlashRegistryType();
    }

    public static TryRemoveTalentPacket decode(PacketBuffer buf) {

        TryRemoveTalentPacket newpkt = new TryRemoveTalentPacket();

        newpkt.guid = buf.readString(30);
        newpkt.registryType = SlashRegistryType.valueOf(buf.readString(30));

        return newpkt;
    }

    public static void encode(TryRemoveTalentPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 30);
        tag.writeString(packet.registryType.name(), 30);
    }

    public static void handle(final TryRemoveTalentPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                if (pkt.registryType == SlashRegistryType.PERK) {
                    PlayerTalentsCap.IPlayerTalentsData talents = Load.talents(player);

                    Perk talent = SlashRegistry.Perks().get(pkt.guid);

                    if (talent != null) {
                        talents.tryRemovePoint(talent);
                        MMORPG.sendToClient(new SyncCapabilityToClient(player, CapTypes.TALENTS), player);
                    }
                } else if (pkt.registryType == SlashRegistryType.SPELL_PERK) {

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
