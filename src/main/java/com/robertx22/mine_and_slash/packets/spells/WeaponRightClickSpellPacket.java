package com.robertx22.mine_and_slash.packets.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class WeaponRightClickSpellPacket {

    public String spellID = "";

    private WeaponRightClickSpellPacket() {

    }

    public WeaponRightClickSpellPacket(BaseSpell spell) {

        if (spell != null) {
            this.spellID = spell.GUID();
        }
    }

    public static WeaponRightClickSpellPacket decode(PacketBuffer buf) {

        WeaponRightClickSpellPacket newpkt = new WeaponRightClickSpellPacket();

        newpkt.spellID = buf.readString(30);

        return newpkt;

    }

    public static void encode(WeaponRightClickSpellPacket packet, PacketBuffer tag) {

        tag.writeString(packet.spellID);

    }

    public static void activate(PlayerEntity player, String spellID) {

        GearItemData gear = Gear.Load(player.getHeldItemMainhand());

        if (gear != null) {

            BaseSpell spell = SlashRegistry.Spells()
                .get(spellID);

            if (!spellID.isEmpty() && spell != null) {
                if (spell.isAllowedAsRightClickFor(gear.GetBaseGearType())) {
                    gear.rightClickSpell = spell.GUID();

                }
            } else {
                gear.rightClickSpell = "";
            }

            Gear.Save(player.getHeldItemMainhand(), gear);
        }

    }

    public static void handle(final WeaponRightClickSpellPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = ctx.get()
                        .getSender();

                    activate(player, pkt.spellID);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);
    }

}
