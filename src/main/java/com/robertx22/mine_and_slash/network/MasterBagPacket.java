package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.items.bags.master_bag.ContainerMasterBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.NamedMasterBag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Supplier;

public class MasterBagPacket {

    public ContainerMasterBag.ItemType type;

    public MasterBagPacket() {

    }

    public MasterBagPacket(ContainerMasterBag.ItemType type) {
        this.type = type;

    }

    public static MasterBagPacket decode(PacketBuffer buf) {

        MasterBagPacket newpkt = new MasterBagPacket();

        newpkt.type = ContainerMasterBag.ItemType.valueOf(buf.readString(20));

        return newpkt;

    }

    public static void encode(MasterBagPacket packet, PacketBuffer tag) {

        tag.writeString(packet.type.toString());

    }

    public static void handle(final MasterBagPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                NetworkHooks.openGui(player, new NamedMasterBag(player.getHeldItemMainhand(), pkt.type), extraData -> {
                    extraData.writeString(pkt.type.toString());
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
