package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class AllocateTalentPacket {

    public String guid;

    public AllocateTalentPacket() {

    }

    public AllocateTalentPacket(Perk talent) {
        this.guid = talent.GUID();
    }

    public static AllocateTalentPacket decode(PacketBuffer buf) {

        AllocateTalentPacket newpkt = new AllocateTalentPacket();

        newpkt.guid = buf.readString();

        return newpkt;

    }

    public static void encode(AllocateTalentPacket packet, PacketBuffer tag) {

        tag.writeString(packet.guid);

    }

    public static void handle(final AllocateTalentPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                PlayerTalentsCap.IPlayerTalentsData talents = Load.talents(player);

                Perk talent = SlashRegistry.Perks().get(pkt.guid);

                if (talent != null) {

                    EntityCap.UnitData data = Load.Unit(player);

                    if (talents.canAllocatePoint(talent, data)) {
                        talents.allocate(talent);
                    }

                    MMORPG.sendToClient(new SyncCapabilityToClient(player, CapTypes.TALENTS), player);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
