package com.robertx22.mine_and_slash.packets.trader;

import com.robertx22.mine_and_slash.gui.trader.TraderData;
import com.robertx22.mine_and_slash.gui.trader.TraderEntity;
import com.robertx22.mine_and_slash.uncommon.datasaving.TraderSaving;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ClientOnly;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TraderPacket {

    public TraderData data;
    public int traderID;

    public TraderPacket() {

    }

    public TraderPacket(TraderData data, TraderEntity en) {
        this.data = data;
        this.traderID = en.getEntityId();
    }

    public static TraderPacket decode(PacketBuffer buf) {

        TraderPacket newpkt = new TraderPacket();

        newpkt.data = TraderSaving.Load(buf.readCompoundTag());
        newpkt.traderID = buf.readInt();

        return newpkt;

    }

    public static void encode(TraderPacket packet, PacketBuffer tag) {

        CompoundNBT nbt = new CompoundNBT();
        TraderSaving.Save(nbt, packet.data);

        tag.writeCompoundTag(nbt);
        tag.writeInt(packet.traderID);

    }

    public static void handle(final TraderPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    ClientOnly.openOrUpdateTradeGui(pkt.data, pkt.traderID);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);
    }

}
