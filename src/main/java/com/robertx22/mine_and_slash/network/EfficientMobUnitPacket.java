package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.UnitNbt;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class EfficientMobUnitPacket {

    public int id;

    public int level;
    public int rarity;
    public boolean preventLoot;
    public EntityTypeUtils.EntityType type = EntityTypeUtils.EntityType.PLAYER;
    public Unit unit;

    public EfficientMobUnitPacket() {

    }

    public EfficientMobUnitPacket(Entity entity, EntityCap.UnitData data) {
        this.id = entity.getEntityId();

        this.unit = data.getUnit();
        this.level = data.getLevel();
        this.rarity = data.getRarity();
        this.preventLoot = !data.shouldDropLoot();
        this.type = data.getType();
    }

    public static EfficientMobUnitPacket decode(PacketBuffer buf) {

        EfficientMobUnitPacket newpkt = new EfficientMobUnitPacket();

        newpkt.id = buf.readInt();
        newpkt.level = buf.readInt();
        newpkt.rarity = buf.readInt();
        newpkt.preventLoot = buf.readBoolean();
        newpkt.type = EntityTypeUtils.EntityType.valueOf(buf.readString(15));

        newpkt.unit = UnitNbt.Load(buf.readCompoundTag());

        return newpkt;

    }

    public static void encode(EfficientMobUnitPacket packet, PacketBuffer tag) {

        CompoundNBT nbt = new CompoundNBT();
        UnitNbt.Save(nbt, packet.unit);

        tag.writeInt(packet.id);
        tag.writeInt(packet.level);
        tag.writeInt(packet.rarity);
        tag.writeBoolean(packet.preventLoot);
        tag.writeString(packet.type.name());

        tag.writeCompoundTag(nbt);

    }

    public static void handle(final EfficientMobUnitPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                Entity entity = MMORPG.proxy.getPlayerEntityFromContext(ctx).world.getEntityByID(pkt.id);

                if (entity instanceof LivingEntity) {

                    LivingEntity en = (LivingEntity) entity;

                    Load.Unit(en).syncEfficientMobPacket(pkt);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
