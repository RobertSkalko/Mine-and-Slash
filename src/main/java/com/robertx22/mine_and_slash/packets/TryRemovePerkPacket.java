package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TryRemovePerkPacket {

    public String guid;

    SlashRegistryType registryType;

    public TryRemovePerkPacket() {

    }

    public TryRemovePerkPacket(BasePerk perk) {
        this.guid = perk.GUID();
        registryType = perk.getSlashRegistryType();
    }

    public static TryRemovePerkPacket decode(PacketBuffer buf) {

        TryRemovePerkPacket newpkt = new TryRemovePerkPacket();

        newpkt.guid = buf.readString(30);
        newpkt.registryType = SlashRegistryType.valueOf(buf.readString(30));

        return newpkt;
    }

    public static void encode(TryRemovePerkPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 30);
        tag.writeString(packet.registryType.name(), 30);
    }

    public static void handle(final TryRemovePerkPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                if (pkt.registryType == SlashRegistryType.PERK) {
                    PlayerTalentsCap.IPlayerTalentsData talents = Load.talents(player);

                    Perk talent = SlashRegistry.Perks().get(pkt.guid);

                    if (talent != null) {
                        talents.tryRemovePoint(talent, player);
                        MMORPG.sendToClient(new SyncCapabilityToClient(player, PlayerCaps.TALENTS), player);
                    }
                } else if (pkt.registryType == SlashRegistryType.SPELL_PERK) {
                    PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                    SpellPerk perk = SlashRegistry.SpellPerks().get(pkt.guid);

                    if (perk != null) {
                        spells.tryRemovePoint(perk, player);
                        MMORPG.sendToClient(new SyncCapabilityToClient(player, PlayerCaps.SPELLS), player);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
