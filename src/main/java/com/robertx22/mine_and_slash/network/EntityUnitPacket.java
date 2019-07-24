package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class EntityUnitPacket {

    public int id;
    public CompoundNBT nbt;

    public EntityUnitPacket() {

    }

    public EntityUnitPacket(Entity entity) {
        this.id = entity.getEntityId();
        this.nbt = Load.Unit(entity).getNBT();
    }

    public EntityUnitPacket(Entity entity, UnitData data) {
        this.id = entity.getEntityId();
        this.nbt = data.getNBT();
    }

    public static EntityUnitPacket decode(PacketBuffer buf) {

        EntityUnitPacket newpkt = new EntityUnitPacket();

        newpkt.id = buf.readInt();
        newpkt.nbt = buf.readCompoundTag();

        return newpkt;

    }

    public static void encode(EntityUnitPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.id);
        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final EntityUnitPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                Entity entity = MMORPG.proxy.getPlayerEntityFromContext(ctx).world.getEntityByID(pkt.id);

                if (entity instanceof LivingEntity) {

                    LivingEntity en = (LivingEntity) entity;

                    Load.Unit(en).setNBT(pkt.nbt);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
