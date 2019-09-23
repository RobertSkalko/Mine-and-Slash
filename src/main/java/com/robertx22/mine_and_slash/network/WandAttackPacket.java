package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Wand;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.WeaponSpeedCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WandAttackPacket {

    public BlockPos pos;

    public WandAttackPacket() {

    }

    public WandAttackPacket(PlayerEntity player) {
        this.pos = player.getPosition();

    }

    public static WandAttackPacket decode(PacketBuffer buf) {

        WandAttackPacket newpkt = new WandAttackPacket();

        newpkt.pos = buf.readBlockPos();

        return newpkt;

    }

    public static void encode(WandAttackPacket packet, PacketBuffer tag) {
        tag.writeBlockPos(packet.pos);
    }

    // static int RANGE = 7;

    public static void handle(final WandAttackPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                ServerPlayerEntity e = ctx.get().getSender();
                if (e != null) {

                    WeaponSpeedCap.IWeaponSpeedCap speed = Load.weaponSpeed(e);

                    if (speed.canAttack()) {

                        speed.onAttack(e);

                        int RANGE = 5;

                        Vec3d lookVector = e.getLookVec();

                        List<Entity> entitiesInBoundingBox = e.getEntityWorld()
                                .getEntitiesWithinAABBExcludingEntity(e, e.getBoundingBox()
                                        .grow(lookVector.x * RANGE, lookVector.y * RANGE, lookVector.z * RANGE)
                                        .expand(1F, 1F, 1F))
                                .stream()
                                .filter(x -> x instanceof LivingEntity)
                                .collect(Collectors.toList());

                        GearItemData gear = Gear.Load(e.getHeldItemMainhand());
                        EntityCap.UnitData sourcedata = Load.Unit(e);

                        if (gear != null && gear.GetBaseGearType() instanceof Wand) {

                            for (Entity entity : entitiesInBoundingBox) {
                                EntityCap.UnitData targetdata = Load.Unit(entity);

                                if (sourcedata.isWeapon(gear)) {
                                    if (sourcedata.tryUseWeapon(gear, e)) {
                                        sourcedata.attackWithWeapon(null, e.getHeldItemMainhand(), gear, e, (LivingEntity) entity, targetdata);
                                    }
                                }

                            }

                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}