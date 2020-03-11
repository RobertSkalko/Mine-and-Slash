package com.robertx22.mine_and_slash.packets.trader;

import com.robertx22.mine_and_slash.new_content.trader.TraderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class BuyTraderItemPacket {

    public int item;
    public int traderID;

    public BuyTraderItemPacket() {

    }

    public BuyTraderItemPacket(int item, TraderEntity en) {
        this.item = item;
        this.traderID = en.getEntityId();
    }

    public static BuyTraderItemPacket decode(PacketBuffer buf) {

        BuyTraderItemPacket newpkt = new BuyTraderItemPacket();

        newpkt.item = buf.readInt();
        newpkt.traderID = buf.readInt();

        return newpkt;

    }

    public static void encode(BuyTraderItemPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.item);
        tag.writeInt(packet.traderID);

    }

    public static void handle(final BuyTraderItemPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ServerPlayerEntity player = (ServerPlayerEntity) ctx.get()
                        .getSender();

                    Entity entity = player.world.getEntityByID(pkt.traderID);

                    if (entity instanceof TraderEntity) {

                        TraderEntity en = (TraderEntity) entity;

                        en.tryBuyItem(player, pkt.item);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);
    }

}
