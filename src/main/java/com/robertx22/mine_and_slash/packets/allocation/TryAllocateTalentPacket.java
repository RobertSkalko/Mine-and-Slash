package com.robertx22.mine_and_slash.packets.allocation;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TryAllocateTalentPacket {

    public String guid;

    public TryAllocateTalentPacket() {

    }

    public TryAllocateTalentPacket(BasePerk perk) {
        this.guid = perk.GUID();
    }

    public static TryAllocateTalentPacket decode(PacketBuffer buf) {

        TryAllocateTalentPacket newpkt = new TryAllocateTalentPacket();

        newpkt.guid = buf.readString(50);

        return newpkt;
    }

    public static void encode(TryAllocateTalentPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 50);
    }

    public static void handle(final TryAllocateTalentPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = ctx.get()
                        .getSender();

                    PlayerTalentsCap.IPlayerTalentsData talents = Load.talents(player);

                    Perk perk = SlashRegistry.Perks()
                        .get(pkt.guid);

                    if (perk != null) {

                        EntityCap.UnitData data = Load.Unit(player);

                        if (talents.canAllocatePoint(perk, data)) {
                            talents.allocate(perk);
                        }

                        MMORPG.sendToClient(new SyncCapabilityToClient(player, PlayerCaps.TALENTS), player);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);
    }

}
