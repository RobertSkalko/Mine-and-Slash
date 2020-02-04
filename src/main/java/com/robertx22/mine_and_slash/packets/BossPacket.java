package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class BossPacket {

    public int id;
    public CompoundNBT nbt;

    public BossPacket() {

    }

    public BossPacket(Entity entity) {
        this.id = entity.getEntityId();
        this.nbt = Load.boss(entity).saveToNBT();
    }

    public static BossPacket decode(PacketBuffer buf) {

        BossPacket newpkt = new BossPacket();

        newpkt.id = buf.readInt();
        newpkt.nbt = buf.readCompoundTag();

        return newpkt;

    }

    public static void encode(BossPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.id);
        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final BossPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                Entity entity = MMORPG.proxy.getPlayerEntityFromContext(ctx).world.getEntityByID(pkt.id);

                if (entity instanceof LivingEntity) {
                    LivingEntity en = (LivingEntity) entity;
                    Load.boss(en).loadFromNBT(pkt.nbt);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
