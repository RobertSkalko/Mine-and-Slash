package com.robertx22.mine_and_slash.packets.allocation.talents;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TryRemoveTalentPacket {

    public String guid;

    public TryRemoveTalentPacket() {

    }

    public TryRemoveTalentPacket(BasePerk perk) {
        this.guid = perk.GUID();
    }

    public static TryRemoveTalentPacket decode(PacketBuffer buf) {

        TryRemoveTalentPacket newpkt = new TryRemoveTalentPacket();

        newpkt.guid = buf.readString(30);

        return newpkt;
    }

    public static void encode(TryRemoveTalentPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 30);
    }

    public static void handle(final TryRemoveTalentPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = ctx.get()
                        .getSender();

                    PlayerTalentsCap.IPlayerTalentsData talents = Load.talents(player);

                    Perk talent = SlashRegistry.Perks()
                        .get(pkt.guid);

                    if (talent != null) {
                        talents.tryRemovePoint(talent, player);
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
