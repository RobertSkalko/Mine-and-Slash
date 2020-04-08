package com.robertx22.mine_and_slash.packets.allocation.abilities;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TryRemoveAbilityPointPacket {

    public String guid;

    public TryRemoveAbilityPointPacket() {

    }

    public TryRemoveAbilityPointPacket(IAbility perk) {
        this.guid = perk.GUID();
    }

    public static TryRemoveAbilityPointPacket decode(PacketBuffer buf) {

        TryRemoveAbilityPointPacket newpkt = new TryRemoveAbilityPointPacket();

        newpkt.guid = buf.readString(50);

        return newpkt;
    }

    public static void encode(TryRemoveAbilityPointPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 50);
    }

    public static void handle(final TryRemoveAbilityPointPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = ctx.get()
                        .getSender();

                    PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                    IAbility ability = IAbility.fromId(pkt.guid);

                    if (ability != null) {

                        EntityCap.UnitData data = Load.Unit(player);

                        if (spells.getLevelOf(ability) > 0) {
                            spells.getAbilitiesData()
                                .removePoint(ability);
                        }

                        MMORPG.sendToClient(new SyncCapabilityToClient(player, PlayerCaps.SPELLS), player);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);
    }

}
