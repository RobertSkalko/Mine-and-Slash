package com.robertx22.mine_and_slash.packets.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class HotbarSetupPacket {

    public int number;
    public PlayerSpellsData.Hotbar hotbar;
    public String spellID;

    public HotbarSetupPacket() {

    }

    public HotbarSetupPacket(BaseSpell spell, int num, PlayerSpellsData.Hotbar bar) {
        this.number = num;
        this.hotbar = bar;
        this.spellID = spell.GUID();
    }

    public static HotbarSetupPacket decode(PacketBuffer buf) {

        HotbarSetupPacket newpkt = new HotbarSetupPacket();

        newpkt.number = buf.readInt();
        newpkt.hotbar = PlayerSpellsData.Hotbar.valueOf(buf.readString(30));
        newpkt.spellID = buf.readString(30);

        return newpkt;

    }

    public static void encode(HotbarSetupPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.number);
        tag.writeString(packet.hotbar.name());
        tag.writeString(packet.spellID);

    }

    public static void handle(final HotbarSetupPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

                PlayerSpellsData data = Load.spells(player).getSpellData();

                data.setHotbar(pkt.number, pkt.hotbar, pkt.spellID);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
