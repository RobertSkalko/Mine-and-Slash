package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CastSpellPacket {

    public Integer hotbarNumber;

    public CastSpellPacket() {

    }

    public CastSpellPacket(Integer hotbarNumber) {
        this.hotbarNumber = hotbarNumber;

    }

    public static CastSpellPacket decode(PacketBuffer buf) {

        CastSpellPacket newpkt = new CastSpellPacket();

        newpkt.hotbarNumber = buf.readInt();

        return newpkt;

    }

    public static void encode(CastSpellPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.hotbarNumber);

    }

    public static void handle(final CastSpellPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                if (spells.getSpellData().canCast(pkt.hotbarNumber, player)) {
                    spells.getSpellData().setToCast(pkt.hotbarNumber, player, 0);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
