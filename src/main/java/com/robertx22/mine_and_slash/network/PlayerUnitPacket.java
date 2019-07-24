package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerUnitPacket {

    public PlayerUnitPacket() {

    }

    private CompoundNBT nbt;

    public PlayerUnitPacket(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public static PlayerUnitPacket decode(PacketBuffer buf) {

        PlayerUnitPacket newpkt = new PlayerUnitPacket();
        newpkt.nbt = buf.readCompoundTag();
        return newpkt;

    }

    public static void encode(PlayerUnitPacket packet, PacketBuffer tag) {

        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final PlayerUnitPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                final PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

                if (player != null) {

                    Load.Unit(player).setNBT(pkt.nbt);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
