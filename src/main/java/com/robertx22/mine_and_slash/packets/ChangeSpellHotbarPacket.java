package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ChangeSpellHotbarPacket {

    public String guid;
    public int number;
    public PlayerSpellsData.Hotbar hotbar;

    private ChangeSpellHotbarPacket() {

    }

    public ChangeSpellHotbarPacket(BaseSpell spell, PlayerSpellsData.Hotbar hotbar, int number) {
        this.guid = spell.GUID();
        this.number = number;
        this.hotbar = hotbar;
    }

    public static ChangeSpellHotbarPacket decode(PacketBuffer buf) {

        ChangeSpellHotbarPacket newpkt = new ChangeSpellHotbarPacket();

        newpkt.guid = buf.readString(50);
        newpkt.number = buf.readInt();
        newpkt.hotbar = PlayerSpellsData.Hotbar.valueOf(buf.readString(30));

        return newpkt;
    }

    public static void encode(ChangeSpellHotbarPacket packet, PacketBuffer tag) {
        tag.writeString(packet.guid, 50);
        tag.writeInt(packet.number);
        tag.writeString(packet.hotbar.name(), 30);
    }

    public static void handle(final ChangeSpellHotbarPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                spells.getSpellData().getMap(pkt.hotbar).put(pkt.number, pkt.guid);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
