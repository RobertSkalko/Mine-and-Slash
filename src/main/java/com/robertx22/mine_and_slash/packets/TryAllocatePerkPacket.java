package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TryAllocatePerkPacket {

    public String guid;
    SlashRegistryType registryType;

    public TryAllocatePerkPacket() {

    }

    public TryAllocatePerkPacket(BasePerk perk) {
        this.guid = perk.GUID();
        this.registryType = perk.getSlashRegistryType();
    }

    public static TryAllocatePerkPacket decode(PacketBuffer buf) {

        TryAllocatePerkPacket newpkt = new TryAllocatePerkPacket();

        newpkt.guid = buf.readString(50);
        newpkt.registryType = SlashRegistryType.valueOf(buf.readString(30));

        return newpkt;
    }

    public static void encode(TryAllocatePerkPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 50);
        tag.writeString(packet.registryType.name(), 30);
    }

    public static void handle(final TryAllocatePerkPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                if (pkt.registryType == SlashRegistryType.PERK) {

                    PlayerTalentsCap.IPlayerTalentsData talents = Load.talents(player);

                    Perk perk = SlashRegistry.Perks().get(pkt.guid);

                    if (perk != null) {

                        EntityCap.UnitData data = Load.Unit(player);

                        if (talents.canAllocatePoint(perk, data)) {
                            talents.allocate(perk);
                        }

                        MMORPG.sendToClient(new SyncCapabilityToClient(player, CapTypes.TALENTS), player);
                    }
                }
                if (pkt.registryType == SlashRegistryType.SPELL_PERK) {
                    PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                    SpellPerk perk = SlashRegistry.SpellPerks().get(pkt.guid);

                    if (perk != null) {

                        EntityCap.UnitData data = Load.Unit(player);

                        if (spells.canAllocatePoint(perk, data)) {
                            spells.allocate(perk);
                        }

                        MMORPG.sendToClient(new SyncCapabilityToClient(player, CapTypes.SPELLS), player);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
