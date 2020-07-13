package com.robertx22.mine_and_slash.packets.spells;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SkillGemData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCastingData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.SkillGem;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class HotbarSetupPacket {

    public int number;
    public SpellCastingData.Hotbar hotbar;
    public int invSlot = 0;

    private HotbarSetupPacket() {

    }

    public HotbarSetupPacket(int invSlot, int num, SpellCastingData.Hotbar bar) {
        this.number = num;
        this.hotbar = bar;
        this.invSlot = invSlot;
    }

    public static HotbarSetupPacket decode(PacketBuffer buf) {

        HotbarSetupPacket newpkt = new HotbarSetupPacket();

        newpkt.number = buf.readInt();
        newpkt.hotbar = SpellCastingData.Hotbar.valueOf(buf.readString(30));
        newpkt.invSlot = buf.readInt();

        return newpkt;

    }

    public static void encode(HotbarSetupPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.number);
        tag.writeString(packet.hotbar.name());
        tag.writeInt(packet.invSlot);

    }

    public static void handle(final HotbarSetupPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = ctx.get()
                        .getSender();

                    SpellCastingData data = Load.spells(player)
                        .getCastingData();

                    if (pkt.invSlot < 0) {
                        SkillGemData skillgem = data.getMap(pkt.hotbar)
                            .get(pkt.number);
                        if (skillgem != null) {
                            PlayerUtils.giveItem(skillgem.toItemStack(), player);
                            data.getMap(pkt.hotbar)
                                .remove(pkt.number);
                        }
                    } else {
                        ItemStack stack = player.inventory.mainInventory.get(pkt.invSlot);

                        SkillGemData skillgem = SkillGem.Load(stack);

                        if (skillgem != null) {

                            stack.shrink(1);

                            data.setHotbar(pkt.number, pkt.hotbar, skillgem);

                            MMORPG.sendToClient(new SyncCapabilityToClient(player, PlayerCaps.SPELLS), player);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);
    }

}
