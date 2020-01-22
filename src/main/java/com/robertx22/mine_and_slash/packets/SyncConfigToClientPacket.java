package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.saveclasses.ListStringData;
import com.robertx22.mine_and_slash.uncommon.datasaving.ListStringSaving;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncConfigToClientPacket {

    public ListStringData configData;
    public ConfigRegister.Config configType;

    public SyncConfigToClientPacket() {

    }

    public SyncConfigToClientPacket(ListStringData configData, ConfigRegister.Config configType) {
        this.configData = configData;
        this.configType = configType;

    }

    public static SyncConfigToClientPacket decode(PacketBuffer buf) {

        SyncConfigToClientPacket newpkt = new SyncConfigToClientPacket();
        newpkt.configData = ListStringSaving.Load(buf.readCompoundTag());
        newpkt.configType = ConfigRegister.Config.valueOf(buf.readString());
        return newpkt;

    }

    public static void encode(SyncConfigToClientPacket packet, PacketBuffer buf) {

        CompoundNBT nbt = new CompoundNBT();
        ListStringSaving.Save(nbt, packet.configData);

        buf.writeCompoundTag(nbt);
        buf.writeString(packet.configType.name());

    }

    public static void handle(final SyncConfigToClientPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                ConfigRegister.CONFIGS.get(pkt.configType).loadFromJsons(pkt.configData.list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}