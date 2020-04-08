package com.robertx22.mine_and_slash.packets.allocation.schools;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TryAddSchoolPointPacket {

    public String school;

    public TryAddSchoolPointPacket() {

    }

    public TryAddSchoolPointPacket(SpellSchools school) {
        this.school = school.name();
    }

    public static TryAddSchoolPointPacket decode(PacketBuffer buf) {

        TryAddSchoolPointPacket newpkt = new TryAddSchoolPointPacket();

        newpkt.school = buf.readString(50);

        return newpkt;
    }

    public static void encode(TryAddSchoolPointPacket packet, PacketBuffer tag) {
        tag.writeString(packet.school, 50);
    }

    public static void handle(final TryAddSchoolPointPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = ctx.get()
                        .getSender();

                    PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                    SpellSchools school = SpellSchools.valueOf(pkt.school);

                    if (school != null) {

                        EntityCap.UnitData data = Load.Unit(player);

                        if (spells.getAbilitiesData()
                            .canAddPointsToSchool(school, data)) {
                            spells.getAbilitiesData()
                                .addSchoolPoint(school);
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
